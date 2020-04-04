import java.util.Objects;

/**
 * A command that removes a specified book from the library.
 */
public class RemoveCmd extends LibraryCommand {

    /** Whether or not to remove books by author. */
    private boolean matchAuthor;

    /** The argument that is passed to remove by author. */
    protected static final String AUTHOR_ARG = "AUTHOR";

    /** The argument that is passed to remove by title. */
    protected static final String TITLE_ARG = "TITLE";

    /** The string that separates the criteria from the term. */
    protected static final String ARG_DELIM = " ";

    /**
     * Format to be used when displaying number of books removed for a certain
     * author.
     */
    protected static final String AUTHOR_COUNT_FORMAT = "%d books removed for author: %s\n";

    /** Format to be used when a book has been removed by title successfully. */
    protected static final String TITLE_SUCCESS_FORMAT = "%s: removed successfully.\n";

    /** Format to be used when a book could not be removed by title. */
    protected static final String TITLE_NO_SUCCESS_FORMAT = "%s: not found.\n";

    /** The title/author to remove books by. */
    private String term;

    /**
     * Create a remove command.
     * 
     * @param argumentInput argument input is expected to contain either "AUTHOR" or
     *                      "TITLE" and then, separated by a space, the exact string
     *                      to remove.
     * @throws IllegalArgumentException if given arguments are not as expected.
     * @throws NullPointerException     if the given argumentInput is null.
     */
    public RemoveCmd(String argumentInput) {
        super(CommandType.REMOVE, argumentInput);
    }

    /**
     * Parses the given argumentInput and initialises the criteria to remove by and
     * the term to match with.
     * 
     * @param argumentInput is expected to contain AUTHOR_ARG or INPUT_ARG and then
     *                      the term to match with separated by a space.
     * @throws NullPointerException if the given argumentInput is null.
     */
    @Override
    protected boolean parseArguments(String argumentInput) {
        Objects.requireNonNull(argumentInput, Utils.ARG_NULL_ERR);
        var split = Utils.splitAtFirstOccurence(argumentInput, ARG_DELIM);
        switch (split[0]) {
            case TITLE_ARG:
                this.matchAuthor = false;
                break;
            case AUTHOR_ARG:
                this.matchAuthor = true;
                break;
            default:
                return false;
        }
        if (split[1].isEmpty()) {
            return false;
        }
        this.term = split[1];
        return true;
    }

    /**
     * Execute the remove command. This finds all the books by title or author and
     * removes exact matches. It is assumed that each title is unique, so at most
     * one book is removed when removing by title.
     * 
     * @param data the data to remove books from.
     * @throws NullPointerException if the given data is null.
     */
    @Override
    public void execute(LibraryData data) {
        Objects.requireNonNull(data, Utils.DATA_NULL_ERR);
        if (this.matchAuthor) {
            this.executeAuthor(data);
        } else {
            this.executeTitle(data);
        }

    }

    /**
     * Execute the remove command when matching by author.
     * 
     * @param data the data to remove books from.
     */
    private void executeAuthor(LibraryData data) {
        var books = data.getBookData();
        var removed = 0;
        for (int i = 0; i < books.size(); i++) {
            var authors = books.get(i).getAuthors();
            for (String author : authors) {
                if (author.equals(this.term)) {
                    books.remove(i--);
                    removed++;
                    break;
                }
            }
        }
        System.out.printf(AUTHOR_COUNT_FORMAT, removed, this.term);
    }

    /**
     * Execute the remove command when matching by title.
     * 
     * @param data the data to remove books from.
     */
    private void executeTitle(LibraryData data) {
        var books = data.getBookData();
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getTitle().equals(this.term)) {
                books.remove(i);
                System.out.printf(TITLE_SUCCESS_FORMAT, this.term);
                return;
            }
        }
        System.out.printf(TITLE_NO_SUCCESS_FORMAT, this.term);
    }
}