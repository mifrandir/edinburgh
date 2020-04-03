import java.util.Objects;

public class Utils {
    /**
     * Splits a given string at the first occurence of a given delimiter.
     * 
     * If the string does not contain the delimiter, the entire string is split off
     * and the rest is only the empty string.
     * 
     * @param s The string to be split.
     * @param d The delimiter to be split at.
     * @return An array containing exactly two strings, always.
     * @throws NullPointerException     if either argument is null
     * @throws IllegalArgumentException if the delimiter is empty.
     */
    public static String[] splitAtFirstOccurence(String s, String d) {
        Objects.requireNonNull(s, "Given string must not be null.");
        Objects.requireNonNull(d, "Given delimiter must not be null.");
        if (d.isEmpty()) {
            throw new IllegalArgumentException("Given delimiter must not be empty.");
        }
        int firstSpaceIdx = s.indexOf(d);
        String first, rest;
        if (firstSpaceIdx == -1) {
            first = s.strip();
            rest = "";
        } else {
            first = s.substring(0, firstSpaceIdx);
            rest = s.substring(firstSpaceIdx + 1);
        }
        return new String[] { first, rest };
    }

}