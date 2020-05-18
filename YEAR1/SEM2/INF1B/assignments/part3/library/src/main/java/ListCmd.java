import java.util.Objects;

/**
 * A command that lists all the books in the library. There are two options,
 * either just the titles or all the details are listed.
 */
public class ListCmd extends LibraryCommand {

    /** Whether or not all the fields of the BookEntry objects are listed. */
    private boolean isLong;

    /** The argument to pass if the default option is desired. */
    private static final String DEFAULT_ARG = "";

    /** The argument to pass if the short option is desired. */
    private static final String SHORT_ARG = "short";

    /** The argument to pass if the long option is desired. */
    private static final String LONG_ARG = "long";

    /** The format to be used when displaying the book count. */
    protected static final String COUNT_FORMAT = "%d books in library:\n";

    /***
     * Create a list command.
     * 
     * @param argumentInput argument is expected to be empty, "short" or "long".
     * @throws NullPointerException     if the given argumentInput is null.
     * @throws IllegalArgumentException if the given argumentInput is not as
     *                                  expected.
     */
    public ListCmd(final String argumentInput) {
        super(CommandType.LIST, argumentInput);
    }

    /**
     * Parses the given argumentInput and initialises necessary parameters.
     * 
     * @param argumentInput argument input is expected to contain
     * @return true if the given argument could be parsed, false otherwise.
     * @throws NullPointerException if the given argumentInput is null.
     */
    @Override
    protected boolean parseArguments(String argumentInput) {
        Objects.requireNonNull(argumentInput, Utils.ARG_NULL_ERR);
        argumentInput = argumentInput.trim();
        // We need to reset isLong to the default value here as
        // this method might be called multiple times with different
        // inputs (even though the reason for that is unclear).
        this.isLong = false;
        switch (argumentInput) {
            case DEFAULT_ARG:
                return true;
            case SHORT_ARG:
                return true;
            case LONG_ARG:
                this.isLong = true;
                return true;
            default:
                return false;
        }
    }

    /**
     * Execute the list command. This prints all the books in the given data.
     * Depending on the configuration only the titles or all the fields are printed.
     * 
     * @param data the data to display.
     * @throws NullPointerException if the given data is null.
     */
    @Override
    public void execute(final LibraryData data) {
        Objects.requireNonNull(data, Utils.DATA_NULL_ERR);
        final var count = data.getBookData().size();
        if (count == 0) {
            System.out.println(Utils.LIBRARY_EMPTY_MESSAGE);
            return;
        }
        System.out.printf(COUNT_FORMAT, count);
        if (this.isLong) {
            this.executeLong(data);
        } else {
            this.executeShort(data);
        }
    }

    /**
     * Execute the list command in case the long option was picked.
     * 
     * @param data the data to display.
     */
    private void executeLong(final LibraryData data) {
        for (final BookEntry book : data.getBookData()) {
            System.out.println(book);
            System.out.println();
        }
    }

    /**
     * Execute the list command in case the short option was picked.
     * 
     * @param data the data to display.
     */
    private void executeShort(final LibraryData data) {
        for (final BookEntry book : data.getBookData()) {
            System.out.println(book.getTitle());
        }
    }

}