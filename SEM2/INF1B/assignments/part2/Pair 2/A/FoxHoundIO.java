import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * A utility class for the fox hound program.
 *
 * It contains helper functions for all file input / output
 * related operations such as saving and loading a game.
 */
public class FoxHoundIO {
    public static boolean saveGame(String[] players, char nextMove, Path saveFile) {
        if (saveFile == null) {
            throw new NullPointerException("Null save path given in saveGame");
        }
        if (players == null) {
            throw new NullPointerException("Null player array given in saveGame");
        } else {
            // As we are only saving default dim files
            if (players.length != 5) {
                throw new IllegalArgumentException(
                        "Too many players - Assuming illegal dimensions - only 8x8 games can be saved!");
            }
        }

        try {
            // An example of saveFile: Paths.get("game01.txt");
            if (Files.exists(saveFile)) // We won't overwrite
            {
                System.err.println("Error: Filepath already exists.");
                return false;
            }

            FileWriter f = new FileWriter(saveFile.toString());
            String toWrite = nextMove + "";
            for (int i = 0; i < players.length; i++) {
                toWrite += " " + players[i];
            }
            f.write(toWrite);
            f.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    /**
     * Attempts to load a game from a Path object and so update a given players[]
     * @param players The array of all players as board Strings
     * @param saveFile The Path to save to
     * @return The character of the player next to move, or a # character on error.
     */
    public static char loadGame(String[] players, Path saveFile) {
        if (players.length != 5) {
            throw new IllegalArgumentException("Size of players[] is too large!");
        }
        try {
            FileReader reader = new FileReader(saveFile.toString());
            String fileContents = "";
            int data;
            do {
                data = reader.read();
                fileContents += (char) data; // I know this is inefficient
            } while (data != -1);

            fileContents = fileContents.substring(
                    0, fileContents.length() - 1); // Trim off the ￿ from the do
            reader.close();
            String regex = "[FH]( [A-H][1-8]){5}";
            if (!fileContents.matches(regex)) {
                return '#';
            } else {
                String[] newplayers = fileContents.substring(2).split(" ");
                for (int i = 0; i < players.length; i++) {
                    players[i] =
                            newplayers[i]; // Why java takes string indexes byRef and objects byVal
                }
                return fileContents.toCharArray()[0];
            }

        } catch (FileNotFoundException e) {
            return '#';
        } catch (IOException e) {
            // do we throw this?
        }
        return '#';
    }
}
