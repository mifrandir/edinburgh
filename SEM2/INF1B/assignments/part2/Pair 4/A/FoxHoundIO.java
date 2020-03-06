import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * A utility class for the fox hound program.
 *
 * It contains helper functions for all file input / output
 * related operations such as saving and loading a game.
 */
public class FoxHoundIO {
    /**
     * Saves the game to file.
     * @param positions Player pieces.
     * @param turn Who's turn is it.
     * @param path Where to save to.
     * @return Whether saving was successful.
     */
    public static boolean saveGame(String[] positions, char turn, Path path) {
        for (String pos : positions) { // Check all pieces are on board.
            if (!FoxHoundUtils.tileIsOnBoard(8, FoxHoundUtils.strToPos(pos))) {
                throw new IllegalArgumentException(
                        "Can't save board state as board state invalid.");
            }
        }
        if (positions.length != 5) {
            throw new IllegalArgumentException("Invalid number of players to save. Need 5.");
        }
        if (!FoxHoundUtils.isValidTurn(turn)) {
            throw new IllegalArgumentException("Invalid turn type.");
        }
        if (Files.exists(path)) { // Don't overwrite existing file.
            System.out.println("Failed to save. File already exists.");
            return false;
        }
        try { // If any error occurs we want to return a failure.
            BufferedWriter writer =
                    new BufferedWriter(new FileWriter(path.toFile())); // Init writer.
            String output = turn + ""; // Prepare to write turn to file.
            for (String pos : positions) {
                output = output + " " + pos; // Prepare to write all the piece positions to file.
            }
            writer.write(output); // Actually write to file.

            writer.close(); // Close file.
            return true; // If it gets to here, saving was successful.
        } catch (Exception e) { // If any error happens, saving was unsuccessful.
            System.out.println("Failed to write to file: " + e);
            return false;
        }
    }

    /**
     * Loads the game from file.
     * @param positions Player positions.
     * @param path Where to load from.
     * @return Who's turn it is.
     */
    public static char loadGame(String[] positions, Path path) {
        if (positions.length != 5) {
            throw new IllegalArgumentException("Can only load games with 5 players!");
        }
        try {
            BufferedReader br = new BufferedReader(new FileReader(path.toFile())); // Init reader.

            String st = br.readLine(); // Only one line so this reading method is ok.
            if (st == null) { // If failed to read file.
                System.out.println("Invalid file.");
                return '#';
            }

            String[] contents = st.split(" "); // Every thing in file is separated by a space.
            if (contents.length != 6) { // Must be exactly 6 space separated items in file.
                System.out.println("Invalid file.");
                return '#';
            }
            String turn_str = contents[0]; // First thing in file is who's turn it is.
            if (turn_str.length() != 1) { // Who's turn wasn't one character long.
                System.out.println("Invalid file. Invalid turn field length.");
                return '#';
            }
            char turn = turn_str.charAt(0);
            for (int i = 1; i < contents.length; i++) { // First check the contents can be read.
                                                        // We're not modifying anything yet.
                int[] pos = FoxHoundUtils.strToPos(contents[i]);
                if (pos == null) { // Check the coords are valid coords.
                    System.out.println("Invalid file.");
                    return '#';
                }
                if (!FoxHoundUtils.tileIsOnBoard(8, pos)) { // Check the coords are on board.
                    System.out.println("Invalid file. Player off board.");
                    return '#';
                }
            }
            if (!FoxHoundUtils.isValidTurn(turn)) {
                System.out.println("Invalid file. Invalid turn char.");
                return '#';
            }
            for (int i = 1; i < contents.length;
                    i++) { // Actually perform the loading. Now modifying player array.
                positions[i - 1] = contents[i];
            }
            return turn;
        } catch (IOException e) { // If any exceptions occur, loading failed.
            System.out.println("Failed to load from file: " + e);
            return '#';
        }
    }
}
