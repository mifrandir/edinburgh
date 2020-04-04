import java.util.ArrayList;
import java.util.Objects;

/**
 * A command that searches for books based on a case-insensitive substring of
 * the title.
 */
public class SearchCmd extends LibraryCommand {

    /**
     * The format to be used when no matchin entries where found for a certain term.
     */
    protected static final String NO_MATCHES_FORMAT = "No hits found for search term: %s\n";

    /**
     * Regex that matches all strings containing some whitespace. "\\s" is any
     * whitespace in regex.
     */
    protected static final String CONTAINS_WHITESPACE_REGEX = ".*\\s.*";

    /** The substring to find. */
    private String term;

    /**
     * Create a seach command.
     * 
     * @param argumentInput is expected to contain a non-empty search term.
     * @throws IllegalArgumentException if given argument is not as expected.
     * @throws NullPointerException     if given argumentInput is null.
     */
    public SearchCmd(final String argumentInput) {
        super(CommandType.SEARCH, argumentInput);
    }

    /**
     * Parses the given argumentInput and initialises the search term.
     * 
     * @param argumentInput is expected to contain a valid, non-empty search term.
     * @return true if the given input could be parsed.
     * @throws NullPointerException if the given argumentInput is null.
     */
    @Override
    protected boolean parseArguments(final String argumentInput) {
        Objects.requireNonNull(argumentInput, Utils.ARG_NULL_ERR);
        if (argumentInput.isEmpty()) {
            return false;
        }
        // Only single words are allowed.
        if (argumentInput.matches(CONTAINS_WHITESPACE_REGEX)) {
            return false;
        }
        this.term = argumentInput;
        return true;
    }

    /**
     * Execute the search command. This prints the titles of the books in the given
     * data if they contain the commands term as a substring.
     * 
     * @param data the data to search in.
     * @throws NullPointerException if the given data is null.
     */
    @Override
    public void execute(final LibraryData data) {
        Objects.requireNonNull(data, Utils.DATA_NULL_ERR);
        // Note: This would be a single, very simple line of code if I was allowed to
        // use lambdas and streams. Instead, we're goind to do it the hard way.
        final var books = data.getBookData();
        final var hits = new ArrayList<String>();
        final var lower = this.term.toLowerCase();
        for (final BookEntry book : books) {
            final var title = book.getTitle();
            if (title.toLowerCase().contains(lower)) {
                hits.add(title);
            }
        }
        if (hits.isEmpty()) {
            System.out.printf(NO_MATCHES_FORMAT, this.term);
            return;
        }
        for (final String title : hits) {
            System.out.println(title);
        }
    }
}