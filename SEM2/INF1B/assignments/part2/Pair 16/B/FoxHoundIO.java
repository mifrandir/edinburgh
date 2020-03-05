/**
 * A utility class for the fox hound program.
 *
 * It contains helper functions for all file input / output
 * related operations such as saving and loading a game.
 */
import java.io.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Scanner; // Import the Scanner class
import java.util.Scanner;

public class FoxHoundIO {
    //    public static void main(String[] args){
    //        String[] f = new String[]{"B1","D1","F1","H1","E8"};
    //        Path input = Paths.get("game01.txt");
    //        char c = 'H';
    //        String[] expectedPlayers = {"C2", "D1", "F1", "H1", "D7"};
    //        String[] expected = {"B3", "C2", "F1", "H3", "F3"};
    //        String[] player1 = expected.clone();

    // saveGame(expectedPlayers, c,input);
    // loadGame(f,input);
    // }

    public static boolean saveGame(String[] game, char f, Path p) {
        boolean result = true;
        OutputStreamWriter tofile;
        BufferedWriter out;
        if (p.toString().equals("")) {
            throw new IllegalArgumentException("Path can not be empty");
        } else if (game.length >= 6) {
            throw new IllegalArgumentException("Can not exceed 5");
        } else if (f == '#') {
            return result;
        }

        try {
            File a = new File(p.toString());
            String path = a.getAbsolutePath();
            if (a.exists()) {
                a.delete();
                a.createNewFile();
            }

            tofile = new OutputStreamWriter(new FileOutputStream(path), "gbk");
            out = new BufferedWriter(tofile);
            out.write(f);
            for (int i = 0; i < game.length; i++) {
                out.write(" " + game[i]);
            }

            out.flush();
            out.close();
        } catch (IOException e) {
            result = false;
        }
        return result;
    }

    public static char loadGame(String[] game, Path p) {
        char result = ' ';
        String a = "";
        File b = new File(p.toString());
        String[] game1 = {};
        String path = b.getAbsolutePath();
        Path bd = Paths.get(path);
        if (isValidCondition(game, p)) {
            a = readFile(b, path);
        } else {
            result = '#';
        }

        try {
            game1 = a.split(" ");

            if (secondCondition(game1)) {
                result = game1[0].charAt(0);
                String[] ne = Arrays.copyOfRange(game1, 1, game1.length);
                saveGame(ne, result, bd);
            }

        } catch (NullPointerException | NoSuchElementException e) {
            System.err.println("Error");
        }

        return result;
    }

    public static String readFile(File p, String path) {
        StringBuilder content = new StringBuilder("");
        InputStream in;
        InputStreamReader is;
        BufferedReader inTwo;
        try {
            String code = resolveCode(path);
            in = new FileInputStream(p);
            is = new InputStreamReader(in, code);
            inTwo = new BufferedReader(is);
            String str = "";
            while (null != (str = inTwo.readLine())) {
                content.append(str);
            }
        } catch (Exception e) {
            System.out.println("Error");
        }
        String fin = content.toString();

        return fin;
    }

    public static String resolveCode(String path) throws Exception {
        InputStream inputStream = new FileInputStream(path);
        byte[] head = new byte[3];
        inputStream.read(head);
        String code = "gb2312"; //或GBK
        if (head[0] == -1 && head[1] == -2)
            code = "UTF-16";
        else if (head[0] == -2 && head[1] == -1)
            code = "Unicode";
        else if (head[0] == -17 && head[1] == -69 && head[2] == -65)
            code = "UTF-8";

        inputStream.close();

        return code;
    }

    public static boolean isValidCondition(String[] game, Path p) {
        boolean result = true;
        File a = new File(p.toString());
        if (game == null) {
            System.err.println("Loaded player array not as expected.");
            result = false;
            throw new NullPointerException("Loaded player array not as expected.");
        } else if (game.length != 5) {
            result = false;
            throw new IllegalArgumentException("Can not exceed 5");

        } else if (!a.exists()) {
            result = false;
            return result;
        }
        return result;
    }

    public static boolean secondCondition(String[] game1) {
        boolean result = true;
        try {
            if (game1.length != 6) {
                result = false;
            } else if (!game1[0].equals("H") && !game1[0].equals("F") && !game1[0].equals("#")) {
                result = false;
            }
            for (int i = 1; i < game1.length; i++) {
                if (game1[i].length() != 2) {
                    result = false;
                } else if (!FoxHoundUI.isValidBoard(8, game1[i])) {
                    result = false;
                }
            }
        } catch (NullPointerException | NoSuchElementException e) {
            System.err.println("Error");
        }
        return result;
    }
}
