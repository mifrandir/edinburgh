import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.List;
import java.util.Arrays;

public class GroupCmd extends LibraryCommand {

    private static final int TITLE_GROUP_COUNT = 27;
    private boolean byAuthor;

    public GroupCmd(final String argumentInput) {
        super(CommandType.GROUP, argumentInput);
    }

    @Override
    protected boolean parseArguments(final String argumentInput) {
        Objects.requireNonNull(argumentInput, "Given argument must not be null.");
        switch (argumentInput) {
            case "TITLE":
                this.byAuthor = false;
                return true;
            case "AUTHOR":
                this.byAuthor = true;
                return true;
            default:
                return false;
        }
    }

    @Override
    public void execute(final LibraryData data) {
        Objects.requireNonNull(data, "Given data must not be null.");
        if (this.byAuthor) {
            this.executeAuthor(data);
        } else {
            this.executeTitle(data);
        }
    }

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

    private void printGroup(String identifier, List<String> contents) {
        if (contents.isEmpty()) {
            return;
        }
        System.out.printf("## %s\n", identifier);
        for (String title : contents) {
            System.out.printf("    %s\n", title);
        }
    }

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
        // Getting an ordered array of keys.
        var keys = groups.keySet().toArray(new String[groups.size()]);
        Arrays.sort(keys);
        for (String key : keys) {
            this.printGroup(key, groups.get(key));
        }
    }
}