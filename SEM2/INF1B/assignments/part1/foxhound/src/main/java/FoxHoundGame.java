
import java.io.EOFException;
import java.nio.file.Path;
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
     * The main loop of the game. Interactions with the main
     * menu are interpreted and executed here.
     *
     * @param dim the dimension of the game board
     * @param players current position of all figures on the board in board coordinates
     */
    private static void gameLoop(int dim, String[] players) throws EOFException {
        // start each game with the Fox
        char turn = FoxHoundUtils.FOX_FIELD;
        boolean exit = false;
        while (!exit) {
            System.out.println("\n#################################");
            FoxHoundUI.displayBoard(players, dim);
            FoxHoundUI.displayPositions(players, dim);

            int choice = FoxHoundUI.mainMenuQuery(turn, STDIN_SCAN);

            // handle menu choice
            switch (choice) {
                case FoxHoundUI.MENU_MOVE:
                    String[] move;
                    while (true) {
                        move = FoxHoundUI.positionQuery(dim, STDIN_SCAN);
                        try {
                            if (FoxHoundUtils.isValidMove(dim, players, turn, move[0], move[1])) {
                                break;
                            }
                        } catch (IllegalArgumentException e) {
                        }
                        System.out.println(FoxHoundUI.COORD_IN_ERROR);
                    }
                    for (int i = 0; i < players.length; i++) {
                        if (players[i].equals(move[0])) {
                            players[i] = move[1];
                            break;
                        }
                    }
                    turn = swapPlayers(turn);
                    break;
                case FoxHoundUI.MENU_SAVE:
                    Path file = FoxHoundUI.fileQuery(STDIN_SCAN);
                    FoxHoundIO.saveGame(players, turn, file);
                    break;
                case FoxHoundUI.MENU_EXIT:
                    exit = true;
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
        int dimension;
        if (args.length > 0) {
            dimension = Integer.parseInt(args[0]);
        } else {
            dimension = FoxHoundUtils.DEFAULT_DIM;
        }

        String[] players = FoxHoundUtils.initialisePositions(dimension);
        try {
            gameLoop(dimension, players);
        } catch (EOFException e) {
            System.err.println("Ran out of lines to read on stdin:\n" + e.getMessage());
        }

        // Close the scanner reading the standard input stream
        STDIN_SCAN.close();
    }
}
