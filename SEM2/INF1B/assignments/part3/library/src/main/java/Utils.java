import java.util.Objects;

/**
 * A class containing utilities like constants and functions for the entire
 * library.
 */
public class Utils {

    /**
     * Error message to be displayed when a null argument string is passed to a
     * function.
     */
    public static final String ARG_NULL_ERR = "The given argument must not be null.";

    /**
     * Error message to be displayed when a null-object is passed as data to a
     * function.
     */
    public static final String DATA_NULL_ERR = "The given data must not be null.";
    
    /** The message to be displayed if the given library is empty. */
    protected static final String LIBRARY_EMPTY_MESSAGE = "The library has no book entries.";

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