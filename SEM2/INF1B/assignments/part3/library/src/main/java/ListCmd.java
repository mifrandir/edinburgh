import java.util.Objects;

public class ListCmd extends LibraryCommand {

    private boolean isLong;

    public ListCmd(String argumentInput) {
        super(CommandType.LIST, argumentInput);
    }

    @Override
    protected boolean parseArguments(String argumentInput) {
        Objects.requireNonNull(argumentInput, "Given argument must not be null.");
        // We need to reset isLong to the default value here as
        // this method might be called multiple times with different
        // inputs (even though the reason for that is unclear).
        this.isLong = false;
        switch (argumentInput) {
            case "short":
                return true;
            case "":
                return true;
            case "long":
                this.isLong = true;
                return true;
            default:
                return false;
        }
    }

    @Override
    public void execute(LibraryData data) {
        Objects.requireNonNull(data, "Given data must not be null.");
        var count = data.getBookCount();
        if (count == 0) {
            System.out.println("The library has no book entries.");
            return;
        }
        System.out.printf("%d books in library:\n", count);
        if (this.isLong) {
            this.executeLong(data);
        } else {
            this.executeShort(data);
        }
    }

    private void executeLong(LibraryData data) {
        for (BookEntry book : data.getBookData()) {
            System.out.println(book);
            System.out.println();
        }
    }

    private void executeShort(LibraryData data) {
        for (BookEntry book : data.getBookData()) {
            System.out.println(book.getTitle());
        }
    }

}