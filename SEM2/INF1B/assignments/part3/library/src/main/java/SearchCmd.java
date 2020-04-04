import java.util.ArrayList;
import java.util.Objects;

/**
 * A command that searches for books based on a case-insensitive substring of
 * the title.
 */
public class SearchCmd extends LibraryCommand {

    /** The substring to find. */
    private String term;

    /**
     * Create a seach command.
     * 
     * @param argumentInput is expected to contain a non-empty search term.
     * @throws IllegalArgumentException if given argument is not as expected.
     * @throws NullPointerException     if given argumentInput is null.
     */
    public SearchCmd(String argumentInput) {
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
    protected boolean parseArguments(String argumentInput) {
        Objects.requireNonNull(argumentInput, "Given argument must not be null.");
        if (argumentInput.isEmpty()) {
            return false;
        }
        // Only single words are allowed. ("\\s" is the regex term for whitespace)
        // Source:
        // https://howtodoinjava.com/java-programs/remove-all-white-spaces-from-string/
        if (argumentInput.matches(".*\\s.*")) {
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
    public void execute(LibraryData data) {
        Objects.requireNonNull(data, "Given data must not be null.");
        // Note: This would be a single, very simple line of code if I was allowed to
        // use lambdas and streams. Instead, we're goind to do it the hard way.
        var books = data.getBookData();
        var hits = new ArrayList<String>();
        var lower = this.term.toLowerCase();
        for (BookEntry book : books) {
            var title = book.getTitle();
            if (title.toLowerCase().contains(lower)) {
                hits.add(title);
            }
        }
        if (hits.isEmpty()) {
            System.out.printf("No hits found for search term: %s\n", this.term);
            return;
        }
        for (String title : hits) {
            System.out.println(title);
        }
    }
}