import java.nio.file.Path;
import java.util.Arrays;
import java.util.Scanner;

/**
 * The Main class of the fox hound program.
 *
 * It contains the main game loop where main menu interactions
 * are processed and handler functions are called.
 */
public class FoxHoundGame {
    /**
     * This scanner can be used by the program to read from
     * the standard input.
     *
     * Every scanner should be closed after its use, however, if you do
     * that for StdIn, it will close the underlying input stream as well
     * which makes it difficult to read from StdIn again later during the
     * program.
     *
     * Therefore, it is advisable to create only one Scanner for StdIn
     * over the course of a program and only close it when the program
     * exits. Additionally, it reduces complexity.
     */
    private static final Scanner STDIN_SCAN = new Scanner(System.in);

    /**
     * Swap between fox and hounds to determine the next
     * figure to move.
     *
     * @param currentTurn last figure to be moved
     * @return next figure to be moved
     */
    private static char swapPlayers(char currentTurn) {
        if (currentTurn == FoxHoundUtils.FOX_FIELD) {
            return FoxHoundUtils.HOUND_FIELD;
        } else {
            return FoxHoundUtils.FOX_FIELD;
        }
    }

    /**
     * Carries out the move on the list of players.
     * @param players List of players to affect with movement.
     * @param source Where the move is from.
     * @param destination Where the move is to.
     */
    private static void execute_move(String[] players, String source, String destination) {
        int player_index = -1;
        for (int i = 0; i < players.length;
                i++) { // Perform a search on players for one matching source.
            if (players[i].equals(source)) {
                player_index = i;
                break; // Already found so more iterations are unnecessary.
            }
        }
        if (player_index == -1) { // Player hasn't been found so index hasn't changed.
            throw new IllegalArgumentException(
                    "Can't move from a tile without a player's piece on!");
        }
        players[player_index] = destination; // Move the piece.
    }
    /**
     * The main loop of the game. Interactions with the main
     * menu are interpreted and executed here.
     *
     * @param dim the dimension of the game board
     * @param players current position of all figures on the board in board coordinates
     */
    private static void gameLoop(int dim, String[] players) {
        // start each game with the Fox
        char turn = FoxHoundUtils.FOX_FIELD;
        boolean exit = false;
        while (!exit) {
            System.out.println("\n#################################");
            FoxHoundUI.displayBoard(players, dim);

            int choice = FoxHoundUI.mainMenuQuery(turn, STDIN_SCAN);

            // handle menu choice
            switch (choice) {
                case FoxHoundUI.MENU_MOVE:
                    String[] valid_inputs; // To fill with the validated player inputs.
                    while (true) { // Keep looping until valid inputs are found.
                        valid_inputs =
                                FoxHoundUI.positionQuery(dim, STDIN_SCAN); // Ask player for inputs.
                        if (FoxHoundUtils.isValidMove(dim, players, turn, valid_inputs[0],
                                    valid_inputs[1])) { // If input is valid.
                            break; // Valid move found. No need to ask again.
                        } else {
                            System.out.println("Invalid move. Try again.");
                        }
                    }
                    execute_move(players, valid_inputs[0], valid_inputs[1]); // Carry out the move.
                    if (FoxHoundUtils.isFoxWinPlayers(players)) { // Check for fox win.
                        System.out.println("Fox wins!");
                        exit = true;
                        break;
                    }
                    if (FoxHoundUtils.isHoundWin(players, dim)) { // Check for hound nwin.
                        System.out.println("Hounds win!");
                        exit = true;
                        break;
                    }

                    turn = swapPlayers(turn);
                    break;
                case FoxHoundUI.MENU_EXIT:
                    exit = true;
                    break;
                case FoxHoundUI.MENU_SAVE:
                    Path save_path = FoxHoundUI.fileQuery(STDIN_SCAN); // Ask user for save path.
                    FoxHoundIO.saveGame(players, turn, save_path);
                    break;
                case FoxHoundUI.MENU_LOAD:
                    Path load_path = FoxHoundUI.fileQuery(STDIN_SCAN); // Ask user for load path.
                    char next_turn = FoxHoundIO.loadGame(players, load_path);
                    if (next_turn != '#') { // If loading was valid (and so players would be
                                            // modified), then also update who's turn it is.
                        turn = next_turn;
                    }
                    break;
                default:
                    System.err.println("ERROR: invalid menu choice: " + choice);
            }
        }
    }

    /**
     * Entry method for the Fox and Hound game.
     *
     * The dimensions of the game board can be passed in as
     * optional command line argument.
     *
     * If no argument is passed, a default dimension of
     * {@value FoxHoundUtils#DEFAULT_DIM} is used.
     *
     * Dimensions must be between {@value FoxHoundUtils#MIN_DIM} and
     * {@value FoxHoundUtils#MAX_DIM}.
     *
     * @param args contain the command line arguments where the first can be
     * board dimensions.
     */
    public static void main(String[] args) {
        int dimension = FoxHoundUtils.DEFAULT_DIM;

        String[] players = FoxHoundUtils.initialisePositions(dimension);

        gameLoop(dimension, players);

        // Close the scanner reading the standard input stream
        STDIN_SCAN.close();
    }
}
