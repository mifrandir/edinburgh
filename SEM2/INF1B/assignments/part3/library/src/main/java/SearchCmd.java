import java.util.ArrayList;
import java.util.Objects;

public class SearchCmd extends LibraryCommand {
    private String term;

    public SearchCmd(String argumentInput) {
        super(CommandType.SEARCH, argumentInput);
    }

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