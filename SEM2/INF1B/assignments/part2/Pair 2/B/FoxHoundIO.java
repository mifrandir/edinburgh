import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;

/**
 * A utility class for the fox hound program.
 *
 * It contains helper functions for all file input / output
 * related operations such as saving and loading a game.
 */
public class FoxHoundIO {
    /**
     * Loads game data and updates player positions from a given file
     *
     * @param players the array containing the coordinates of all pieces on the board
     * @param input the location of the file trying to be loaded
     * @return 'F' or 'H' depending on whose turn is next in the saved file, '#' if there is a
     *         loading error
     * @throws NullPointerException if the players array is null, contains a null element or the
     *         provided path is null
     * @throws IllegalArgumentException if any of the coordinates in the players array are not
     *         contained within a default 8x8 board
     * @throws IOException if there is an error reading the file
     */
    public static char loadGame(String[] players, Path input) {
        if (players == null) throw new NullPointerException("ERROR: Players array cannot be null.");
        if (FoxHoundUtils.elemsNotNull(players) == false)
            throw new NullPointerException(
                    "ERROR: Players array cannot contain any null elements.");
        if (FoxHoundUtils.validCoordinates(players, 8) == false)
            throw new IllegalArgumentException("ERROR: A players coordinate is not on the board");

        if (input == null) throw new NullPointerException("ERROR: Path cannot be null.");
        if (fileExists(input) == false) return '#';

        File file = input.toFile();
        String loadData = "";
        int noOfLines = 0;
        // read all the data from the given file, counting the number of lines
        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                loadData += sc.nextLine();
                noOfLines++;
            }
            sc.close();
        } catch (IOException e) {
            System.err.printf("ERROR: %s", e);
        }

        // extract the coordinates from the data in the file
        String[] loadDataArray = loadData.split(" ");
        String[] coordsTemp = new String[loadDataArray.length - 1];
        for (int i = 1; i < loadDataArray.length; i++) coordsTemp[i - 1] = loadDataArray[i];

        // if the file is of the correct format, update the players array to contain the coordinates
        // in the file
        if (FoxHoundUtils.isValidFigure(loadDataArray[0].charAt(0))
                && FoxHoundUtils.validCoordinates(coordsTemp, FoxHoundUtils.DEFAULT_DIM)
                && noOfLines == 1) {
            for (int i = 1; i < loadDataArray.length; i++) players[i - 1] = loadDataArray[i];

            return loadDataArray[0].charAt(0);
        }
        // otherwise, return a load error
        else
            return '#';
    }

    /**
     * Saves the current state of the game along with whoever has the next turn to a specified file
     *
     * @param players the array containing the coordinates of all pieces on the board
     * @param nextMove a character representing who was due to go next at time of saving
     * @param saveFile the path at which the file is to be saved
     * @return true if saving is successful, false if not
     * @throws NullPointerException if the players array is null, contains a null element or the
     *         saveFile path is null
     * @throws IllegalArgumentException if the players array contains a coordinate not contained in
     *         a default 8x8 board or if the nextMove char does
     * not represent a fox or a hound
     */

    public static boolean saveGame(String[] players, char nextMove, Path saveFile) {
        if (players == null) throw new NullPointerException("ERROR: Players array cannot be null.");
        if (FoxHoundUtils.elemsNotNull(players) == false)
            throw new NullPointerException(
                    "ERROR: Players array cannot contain any null elements.");
        if (FoxHoundUtils.validCoordinates(players, 8) == false)
            throw new IllegalArgumentException("ERROR: A players coordinate is not on the board");
        if (FoxHoundUtils.isValidFigure(nextMove) == false)
            throw new IllegalArgumentException(
                    "ERROR: The next player to move is not a fox or hound.");
        if (saveFile == null) throw new NullPointerException("ERROR: File name cannot be null.");

        // if the file exists, return false as we cannot overwrite
        if (fileExists(saveFile) == true)
            return false;

        else if (fileExists(saveFile) == false) {
            // if the file does exist, check if we can create the file
            if (createSaveFile(saveFile)) return writeFile(players, nextMove, saveFile);
            // if we can't, return false as the file path is invalid
            else
                return false;
        } else
            return false;
    }

    /**
     * Trys to create a file in a given location
     *
     * @param saveFile the path to the file to be saved
     * @return true if the file can be created, false if not
     * @throws IOException if there is an error when trying to create the file
     */
    public static boolean createSaveFile(Path saveFile) {
        File file = null;
        try {
            file = saveFile.toFile();
            file.createNewFile();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    /**
     *Checks if a file exists at a given Path
     *
     * @param saveFile the path to the file we are checking
     * @return true if the file at the path exists, false if not
     */
    public static boolean fileExists(Path saveFile) {
        if (saveFile.toFile().exists())
            return true;
        else
            return false;
    }

    /**
     * Writes the data to be saved to the given file
     *
     * @param players the current location of all players on the board to be written to the save
     *         file
     * @param nextMove a character representing who has the next turn at the time of saving
     * @param saveFile the path to where the file is to be saved
     * @return if the data is successfully written to the file, false if not
     * @throws IOException if there is an error writing to the file or closing it
     */
    public static boolean writeFile(String[] players, char nextMove, Path saveFile) {
        boolean saved = false;
        FileWriter fr = null;
        try {
            fr = new FileWriter(saveFile.toFile());
            fr.write(getSaveData(players, nextMove));
        } catch (IOException e) {
            System.err.printf("ERROR: %s", e);
        } finally {
            try {
                fr.close();
                saved = true;
            } catch (IOException e) {
                System.err.printf("ERROR: %s", e);
            }
        }
        return saved;
    }

    /**
     * Formats the data to be saved into a single string for saving
     *
     * @param players array containing the positions of all players on the board
     * @param nextMove character representing who has the next turn at the time of saving
     * @return a string containing the data to be saved in the proper format
     */
    public static String getSaveData(String[] players, char nextMove) {
        String saveData = "";
        // put next move at the start of the string
        saveData += Character.toUpperCase(nextMove);
        // then add the coordinates in order separated by a space
        for (int i = 0; i < players.length; i++) {
            saveData += " " + players[i];
        }

        return saveData;
    }
}
