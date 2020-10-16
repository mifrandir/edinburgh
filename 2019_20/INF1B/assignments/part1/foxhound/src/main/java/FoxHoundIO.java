import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.text.ParseException;

/**
 * A utility class for the fox hound program.
 *
 * It contains helper functions for all file input / output
 * related operations such as saving and loading a game.
 */
public class FoxHoundIO {
    public static boolean saveGame(String[] players, char turn, Path file) {
        if (players == null || file == null) {
            throw new NullPointerException();
        }
        if (turn != 'H' && turn != 'F') {
            throw new IllegalArgumentException("Given turn was neither 'H' nor 'F': " + turn);
        }
        if (players.length != 5) {
            throw new IllegalArgumentException("Given player array does not have default length.");
        }
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file.toFile()));
            try {
                writer.write(turn);
                for (int i = 0; i < players.length; i++) {
                    writer.write(" " + players[i]);
                }
            } finally {
                if (writer != null) {
                    writer.close();
                }
            }
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    public static char LOAD_ERROR = '#';

    public static char loadGame(String[] players, Path file) {
        if (players == null) {
            throw new NullPointerException();
        }
        if (players.length != 5) {
            throw new IllegalArgumentException(
                    "The given array does not contain 4 hounds and 1 fox.");
        }
        String[] newPlayers;
        char turn = LOAD_ERROR;
        try {
            BufferedReader br = new BufferedReader(new FileReader(file.toFile()));
            try {
                String[] args = br.readLine().split(" ");
                if (args.length != 6) {
                    return LOAD_ERROR;
                }
                if (args[0].length() != 1
                        || (args[0].charAt(0) != 'F' && args[0].charAt(0) != 'H')) {
                    return LOAD_ERROR;
                }
                turn = args[0].charAt(0);
                newPlayers = new String[args.length - 1];
                for (int i = 1; i < args.length; i++) {
                    newPlayers[i - 1] = args[i];
                }
            } finally {
                br.close();
            }
        } catch (IOException e) {
            return LOAD_ERROR;
        }
        for (int i = 0; i < newPlayers.length; i++) {
            try {
                FoxHoundUtils.toBoardIndex(newPlayers[i], 8);
            } catch (Exception e) {
                return LOAD_ERROR;
            }
        }
        for (int i = 0; i < players.length; i++) {
            players[i] = newPlayers[i];
        }
        return turn;
    }
}
