import java.util.Objects;
import java.nio.file.*;

public class AddCmd extends LibraryCommand {
    /** The suffix of a valid data file. */
    private static final String DATA_FILE_SUFFIX = ".csv";

    /** The path to load the data from. */
    private Path path;

    /**
     * Create an add command.
     * 
     * @param argumentInput argument input is expected to contain a valid file path
     *                      ending in DATA_FILE_SUFFIX (".csv").
     * @throws IllegalArgumentException if given arguments are not as expected.
     * @throws NullPointerException     if the given argumentInput is null.
     */
    public AddCmd(final String argumentInput) {
        super(CommandType.ADD, argumentInput);
    }

    /**
     * Parses the given argumentInput to get the file path.
     * 
     * @param argumentInput argument input is expected to contain a valid filed path
     *                      ending in DATA_FILE_SUFFIX (".csv").
     * @return true if the given argument could be parsed, false otherwise.
     * @throws NullPointerException if the given argumentInput is null.
     */
    @Override
    protected boolean parseArguments(final String argumentInput) {
        Objects.requireNonNull(argumentInput, Utils.ARG_NULL_ERR);
        // We are treating the entire argument as one path. This way
        // we do not need to handle any paths including spaces.
        if (!argumentInput.endsWith(DATA_FILE_SUFFIX)) {
            return false;
        }
        this.path = Paths.get(argumentInput);
        return true;
    }

    /**
     * Execute the add command. This reads the values from the given file and
     * inserts them into the given library.
     * 
     * @param data the library to add the new books to.
     * @throws NullPointerException if the given library is null.
     */
    @Override
    public void execute(final LibraryData data) {
        Objects.requireNonNull(data, Utils.DATA_NULL_ERR);
        data.loadData(this.path);
    }
}