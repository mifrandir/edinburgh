import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.List;
import java.util.Arrays;

/**
 * A command that groups commands either by title or by author and prints them
 * to the standard output.
 */
public class GroupCmd extends LibraryCommand {
    /** The count of groups when grouping by title. */
    private static final int TITLE_GROUP_COUNT = 27;

    /** The argument that is passed to group by author. */
    protected static final String AUTHOR_ARG = "AUTHOR";

    /** The argument that is passed to group by title. */
    protected static final String TITLE_ARG = "TITLE";

    /** The indent that is used for group contents. */
    protected static final String INDENT = "    ";

    /** Whether this command groups by Author. */
    private boolean byAuthor;

    /**
     * Create a group command.
     * 
     * @param argumentInput argument input is expected to contain "TITLE" or
     *                      "AUTHOR"
     * @throws IllegalArgumentException if given arguments are invalid
     * @throws NullPointerException     if the given argumentInput is null.
     */
    public GroupCmd(final String argumentInput) {
        super(CommandType.GROUP, argumentInput);
    }

    /**
     * Parses the given command input and initialises necessary parameters.
     * 
     * @param argumentInput argument input is expected to contain AUTHOR_ARG or
     *                      TITLE_ARG
     * @return true if the given argument could be parsed, false otherwise.
     * @throws NullPointerException if the given argumentInput is null.
     */
    @Override
    protected boolean parseArguments(final String argumentInput) {
        Objects.requireNonNull(argumentInput, "Given argument must not be null.");
        switch (argumentInput) {
            case TITLE_ARG:
                this.byAuthor = false;
                return true;
            case AUTHOR_ARG:
                this.byAuthor = true;
                return true;
            default:
                return false;
        }
    }

    /**
     * Execute the group command. This prints the titles of the books in the given
     * data grouped by either their authors or their first letters, depending on the
     * parsed arguments.
     * 
     * @param data the data to display.
     * @throws NullPointerException if the given data is null.
     */
    @Override
    public void execute(final LibraryData data) {
        Objects.requireNonNull(data, "Given data must not be null.");
        if (data.getBookCount() == 0) {
            System.out.println("The library has no book entries.");
            return;
        }
        if (this.byAuthor) {
            this.executeAuthor(data);
        } else {
            this.executeTitle(data);
        }
    }

    /**
     * Execute the group command in case it's supposed to group by title.
     * 
     * @param data the data to display
     */
    private void executeTitle(final LibraryData data) {
        var titles = new ArrayList<String>();
        for (BookEntry book : data.getBookData()) {
            titles.add(book.getTitle());
        }
        var groups = new ArrayList<ArrayList<String>>(TITLE_GROUP_COUNT);
        for (int i = 0; i < TITLE_GROUP_COUNT; i++) {
            groups.add(new ArrayList<String>());
        }
        for (String title : titles) {
            var c = title.charAt(0);
            if (Character.isLetter(c)) {
                int i = Character.toLowerCase(c) - 'a';
                groups.get(i).add(title);
            } else {
                groups.get(TITLE_GROUP_COUNT - 1).add(title);
            }
        }
        System.out.println("Grouped data by TITLE");
        for (int i = 0; i < TITLE_GROUP_COUNT - 1; i++) {
            // Since printGroup uses a String, we need to convert our character to one.
            this.printGroup(Character.toString((char) ('A' + i)), groups.get(i));

        }
        this.printGroup("[0-9]", groups.get(TITLE_GROUP_COUNT - 1));
    }

    /**
     * Prints a given group with an identifier and its contents.
     * 
     * @param identifier The name to display for this particular group.
     * @param contents   The contents to display in this group, line by line.
     */
    private void printGroup(String identifier, List<String> contents) {
        if (contents.isEmpty()) {
            return;
        }
        System.out.printf("## %s\n", identifier);
        for (String title : contents) {
            System.out.printf("%s%s\n", INDENT, title);
        }
    }

    /**
     * Execute the group command in case it's supposed to group by author.
     * 
     * @param data the data to be displayed.
     */
    private void executeAuthor(final LibraryData data) {
        var books = data.getBookData();
        var groups = new HashMap<String, List<String>>();
        for (BookEntry book : books) {
            var title = book.getTitle();
            var authors = book.getAuthors();
            for (String author : authors) {
                // If the key is not yet present, we create a new ArrayList.
                var group = groups.getOrDefault(author, new ArrayList<String>());
                group.add(title);
                groups.put(author, group);
            }
        }
        System.out.println("Grouped data by AUTHOR");
        // Getting an ordered array of keys.
        var keys = groups.keySet().toArray(new String[groups.size()]);
        Arrays.sort(keys);
        for (String key : keys) {
            this.printGroup(key, groups.get(key));
        }
    }
}