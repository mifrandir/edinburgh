import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

/**
 * A utility class for the fox hound program.
 *
 * It contains helper functions for all file input / output
 * related operations such as saving and loading a game.
 */
public class FoxHoundIO {
    /**
     * Loads a saved game file into current game.
     *
     * @param players String array which represent all game pieces positions.
     * @param input a Path object to provide file path for files to be loaded.
     * @return a character representing next figure to move.
     * @throws IllegalArgumentException if positions in position array not possible or length not 5.
     * @throws NullPointerException if players array or path is null.
     */
    public static char loadGame(String[] players, Path input) {
        if (players == null || input == null) {
            throw new NullPointerException("Players array and path cannot be null.");
        }

        if (players.length != 5) {
            throw new IllegalArgumentException(("Players array must have length 5."));
        }

        if (!Files.exists(input)) {
            return '#';
        }
        try {
            for (String player : players) {
                if (!FoxHoundUtils.onBoard(8, player)) {
                    throw new IllegalArgumentException(
                            "Some players in string array not possible.");
                }
            }
            String content = new String(Files.readAllBytes(input));

            String[] parts = content.split(" ", 6);
            for (int i = 1; i < parts.length; i++) {
                if (!FoxHoundUtils.onBoard(8, parts[i])) {
                    return '#';
                }
            }

            if (content.charAt(0) != 'H' && content.charAt(0) != 'F') {
                return '#';
            }

            if (Files.lines(input).count() != 1) {
                return '#';
            }

            if (parts.length - 1 >= 0) System.arraycopy(parts, 1, players, 0, parts.length - 1);

            return content.charAt(0);
        } catch (IOException e) {
            return '#';
        }
    }

    /**
     * Saves current game into specified file.
     *
     * @param players String array which represent all game pieces positions.
     * @param nextMove character (F/H) that will make next move.
     * @param saveFile a Path object to provide file path for files to be saved.
     * @return boolean which determines if game is able to be saved.
     * @throws NullPointerException if players array or path is null.
     * @throws IllegalArgumentException if positions in position array not possible or length not 5,
     * next move isn't H or F, or if saveFile is overriding other save file.
     */
    public static boolean saveGame(String[] players, char nextMove, Path saveFile) {
        if (players == null || saveFile == null) {
            throw new NullPointerException("Players array and path cannot be null.");
        }

        if (players.length != 5) {
            throw new IllegalArgumentException(("Players array must have length 5."));
        }

        for (String s : players) {
            if (!FoxHoundUtils.onBoard(8, s)) {
                throw new IllegalArgumentException("Some players in string array not possible.");
            }
        }

        if (nextMove != 'H' && nextMove != 'F') {
            throw new IllegalArgumentException("Next move should be either F or H.");
        }

        if (Files.exists(saveFile)) {
            throw new IllegalArgumentException("Save file is overriding existing files.");
        }

        String player = Arrays.toString(players).replace(",", "").replace("[", "").replace("]", "");

        try {
            Files.writeString(saveFile, nextMove + " " + player);
            return true;
        } catch (IOException e) {
            System.out.println("Cannot save file.");
            return false;
        }
    }
}
