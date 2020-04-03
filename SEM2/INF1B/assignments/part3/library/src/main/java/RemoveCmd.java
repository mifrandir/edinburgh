import java.util.Objects;

public class RemoveCmd extends LibraryCommand {

    private boolean matchAuthor;
    private String term;

    public RemoveCmd(String argumentInput) {
        super(CommandType.REMOVE, argumentInput);
    }

    @Override
    protected boolean parseArguments(String argumentInput) {
        Objects.requireNonNull(argumentInput, "Given argument must not be null.");
        var split = Utils.splitAtFirstOccurence(argumentInput, LibraryCommand.ARGUMENT_DELIMITER);
        switch (split[0]) {
            case "TITLE":
                this.matchAuthor = false;
                break;
            case "AUTHOR":
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

    @Override
    public void execute(LibraryData data) {
        Objects.requireNonNull(data, "Given data must not be null.");
        if (this.matchAuthor) {
            this.executeAuthor(data);
        } else {
            this.executeTitle(data);
        }

    }

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
        System.out.printf("%d books removed for author: %s\n", removed, this.term);
    }

    private void executeTitle(LibraryData data) {
        var books = data.getBookData();
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getTitle().equals(this.term)) {
                books.remove(i);
                System.out.printf("%s: removed successfully.\n", this.term);
                return;
            }
        }
        System.out.printf("%s: not found.\n", this.term);
    }
}