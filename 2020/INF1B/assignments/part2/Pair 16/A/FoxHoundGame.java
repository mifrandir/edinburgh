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

            if (FoxHoundUtils.isFoxWin(players[players.length - 1]) || FoxHoundUtils.isHoundWin(players, dim)) {
                break;
            }

            int choice = FoxHoundUI.mainMenuQuery(turn, STDIN_SCAN);

            // handle menu choice
            switch (choice) {
                case FoxHoundUI.MENU_MOVE:
                    while (true) {
                        System.out.println(Arrays.toString(players));
                        String[] parts = FoxHoundUI.positionQuery(dim, STDIN_SCAN);
                        if (!FoxHoundUtils.isValidMove(dim, players, turn, parts[0], parts[1])) {
                            System.out.print("ERROR: Invalid move. Try again!\n");
                        } else {
                            updatePlayers(players, parts);
                            turn = swapPlayers(turn);
                            break;
                        }
                    }
                    break;

                case FoxHoundUI.MENU_SAVE:
                    Path location = FoxHoundUI.fileQuery(STDIN_SCAN);
                    if (!FoxHoundIO.saveGame(players, turn, location)) {
                        System.err.println("ERROR: Saving file failed.");
                    }
                    break;

                case FoxHoundUI.MENU_LOAD:
                    try {
                        char temp = FoxHoundIO.loadGame(players, FoxHoundUI.fileQuery(STDIN_SCAN));
                        if (temp == '#') {
                            System.err.println("ERROR: Loading from file failed.");
                            break;
                        } else {
                            turn = temp;
                        }
                    } catch (Exception e) {
                        System.err.println("ERROR: Loading from file failed.");
                    }
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
     * helper function that updates the players array when new position coordinates are used.
     * @param players current position of all figures on the board in board coordinates.
     * @param parts array that contains origin and destination of new position.
     */
    public static void updatePlayers(String[] players, String[] parts) {
        for (int i = 0; i < players.length; i++) {
            if (players[i].equals(parts[0])) {
                players[i] = parts[1];
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
        String choice;
        System.out.println("Would you like to specify a dimension? (y/n)");

        // Asks user for input of either "y" or "n":
        while (true) {
            choice = STDIN_SCAN.nextLine();
            if (!choice.equals("y") && !choice.equals("n")) {
                System.out.println(("Please input either \"y\" or \"n\""));
            } else {
                break;
            }
        }
        // Asks user for dimension between 4 and 26:
        if (choice.equals("y")) {
            System.out.println("Enter dimension between 4 - 26");
            while (true) {
                try {
                    dimension = STDIN_SCAN.nextInt();
                    if (dimension < 4 || dimension > 26) {
                        dimension = FoxHoundUtils.DEFAULT_DIM;
                    }
                    break;
                } catch (Exception e) {
                    System.out.println("Enter an integer between 4 and 26:");
                    STDIN_SCAN.next();
                }
            }
        }

        String[] players = FoxHoundUtils.initialisePositions(dimension);
        gameLoop(dimension, players);

        // Close the scanner reading the standard input stream       
        STDIN_SCAN.close();
    }
}
