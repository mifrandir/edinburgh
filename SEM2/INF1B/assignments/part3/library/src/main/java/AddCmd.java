import java.util.Objects;
import java.nio.file.*;

public class AddCmd extends LibraryCommand {

    private static final String DATA_FILE_SUFFIX = ".csv";

    private Path path;

    public AddCmd(String argumentInput) {
        super(CommandType.ADD, argumentInput);
    }

    @Override
    public void execute(LibraryData data) {
        Objects.requireNonNull(data, "Given data must not be null.");
        data.loadData(this.path);
    }

    @Override
    protected boolean parseArguments(String argumentInput) {
        Objects.requireNonNull(argumentInput, "Given argument must not be null.");
        // We are treating the entire argument as one path. This way
        // we do not need to handle any paths including spaces.
        if (!argumentInput.endsWith(DATA_FILE_SUFFIX)) {
            return false;
        }
        this.path = Paths.get(argumentInput);
        return true;
    }
}