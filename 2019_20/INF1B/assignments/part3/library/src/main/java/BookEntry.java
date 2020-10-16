import java.util.Objects;

/**
 * Immutable class encapsulating data for a single book entry.
 */
public class BookEntry {
    // Constants:

    /**
     * The maximum value for the rating of any book.
     * 
     * This is public as it may be of interest outside for the user of this class.
     */
    public static final float MAX_RATING = 5;

    /** The error to be displayed if the title is null. */
    protected static final String TITLE_NULL_ERR = "The title must not be null.";

    /** The error to be displayed if the authors array is null. */
    protected static final String AUTHORS_NULL_ERR = "The array of authors must not be null.";

    /** The error to be displayed if the authors array is empty. */
    protected static final String AUTHORS_EMPTY_ERR = "There needs to be at least one author in the authors array.";

    /** The error to be displaed if one of the authors is null. */
    protected static final String AUTHOR_NULL_ERR = "Each individual author must not be null.";

    /** The error to be displayed if the rating is negative. */
    protected static final String RATING_NEGATIVE_ERR = "The rating must not be negative.";

    /** The error to be displayed if the rating is too big. */
    protected static final String RATING_EXCEEDED_MAX_ERR = "The rating must not be greater than "

            + Float.toString(MAX_RATING) + ".";
    /** The error to be displayed if the ISBN is null. */
    protected static final String ISBN_NULL_ERR = "The ISBN must not be null.";

    /** The error to be displayed if the page number is negative. */
    protected static final String PAGES_NEGATIVE_ERR = "The number of pages must not be negative.";

    protected static final String STRING_FORMAT = "%s\nby %s\nRating: %.2f\nISBN: %s\n%d pages";

    /** The title of this book. */
    private final String title;

    /** The authors of this book in an array. */
    private final String[] authors;

    /** The rating of this book. Guranteed to be within [0, MAX_RATING]. */
    private final float rating;

    /** The ISBN of this book as a string. */
    private final String ISBN;

    /** The number of pages of this book. Guranteed to be non-negative. */
    private final int pages;

    // Getters for the instance fields:

    /**
     * Method to get the title of the book.
     * 
     * @return A new String object containing the title of the book.
     */
    public String getTitle() {
        return String.valueOf(this.title);
    }

    /**
     * Method to get the authors of the book.
     * 
     * @return A new array containing new String objects for each authors name.
     */
    public String[] getAuthors() {
        // We need to copy the array, as even though the field is final and Strings are
        // immutable, its elements might still be changed, i.e. replaced, outside the
        // class.
        return this.authors.clone();
    }

    /**
     * Method to get the rating of the book.
     * 
     * @return The rating of the book as a floating point number.
     */
    public float getRating() {
        return this.rating;
    }

    /**
     * Method to get the ISBN of this book.
     * 
     * @return A new String object containing the ISBN of the book.
     */
    public String getISBN() {
        return this.ISBN;
    }

    /**
     * Method to get the number of pages of this book.
     * 
     * @return The number of pages of this book as an ordinary int.
     */
    public int getPages() {
        return this.pages;
    }

    // Constructor:
    /**
     * The constructor for a single BookEntry object. The given values may not be
     * changed later.
     * 
     * @param title   A String containing the title of the book.
     * @param authors A String array containing the authors of the book.
     * @param rating  A float between 0 and MAX_RATING representing the rating of
     *                the book.
     * @param ISBN    A String containing the ISBN of the book.
     * @param pages   A non-negative integer representing the number of pages of the
     *                book.
     * @throws NullPointerException     When any of the arguments including the
     *                                  values within the authors array is null.
     * @throws IllegalArgumentException When the rating is not between 0 and
     *                                  MAX_RATING or when the number of pages is
     *                                  negative.
     */
    public BookEntry(final String title, final String[] authors, final float rating, final String ISBN,
            final int pages) {
        BookEntry.checkArgs(title, authors, rating, ISBN, pages);
        this.title = String.valueOf(title);
        this.authors = authors.clone(); // Same here as in getAuthors.
        this.rating = rating;
        this.ISBN = String.valueOf(ISBN);
        this.pages = pages;
    }

    /**
     * The argument checker for the constructor for a single BookEntry object.
     * 
     * @param title   A String containing the title of the book.
     * @param authors A String array containing the authors of the book.
     * @param rating  A float between 0 and MAX_RATING representing the rating of
     *                the book.
     * @param ISBN    A String containing the ISBN of the book.
     * @param pages   A non-negative integer representing the number of pages of the
     *                book.
     * @throws NullPointerException     When any of the arguments including the
     *                                  values within the authors array is null.
     * @throws IllegalArgumentException When the rating is not between 0 and
     *                                  MAX_RATING or when the number of pages is
     *                                  negative.
     */
    private static void checkArgs(final String title, final String[] authors, final float rating, final String ISBN,
            final int pages) {
        // Checking title
        Objects.requireNonNull(title, TITLE_NULL_ERR);
        // Checking authors
        Objects.requireNonNull(authors, AUTHORS_NULL_ERR);
        if (authors.length == 0) {
            throw new IllegalArgumentException(AUTHORS_EMPTY_ERR);
        }
        for (final String author : authors) {
            Objects.requireNonNull(author, AUTHOR_NULL_ERR);
        }
        // Checking rating
        if (rating < 0) {
            throw new IllegalArgumentException(RATING_NEGATIVE_ERR);
        }
        if (rating > MAX_RATING) {
            throw new IllegalArgumentException(RATING_EXCEEDED_MAX_ERR);
        }
        // Checking ISBN
        Objects.requireNonNull(ISBN, ISBN_NULL_ERR);
        // Checking pages
        if (pages < 0) {
            throw new IllegalArgumentException(PAGES_NEGATIVE_ERR);
        }
    }

    /**
     * Method to get a String representation of the BookEntry. The format used is as
     * follows: "%s\nby %s\nRating: %.2f\nISBN: %s\n%d pages"
     * 
     * @return A String representation of the BookEntry with the format described
     *         above.
     */
    @Override
    public String toString() {
        return String.format(STRING_FORMAT, this.title, String.join(", ", this.authors), this.rating, this.ISBN,
                this.pages);
    }

    /**
     * Compares this BookEntry instance to another object. They are equal if, and
     * only if, the object is of type BookEntry and all the fields are equal, too.
     * 
     * @param o is the object this BookEntry instance is compared to.
     * @return Whether the Object is equal to this BookEntry instance.
     */
    @Override
    public boolean equals(final Object o) {
        // Initial null and class checking.
        if (o == null) {
            return false;
        }
        if (o.getClass() != this.getClass()) {
            return false;
        }
        final var other = (BookEntry) o;
        // Checking all fields except authors.
        // This might be hard to read but due to the conditional ORs (||) this ensures
        // that only the necessary checks are run. I don't know whether the Java
        // compiler would optimize this if I used variables, but in this case it's still
        // quite readable, so it does not really hurt.
        if (!this.title.equals(other.title) || !(this.rating == other.rating) || !this.ISBN.equals(other.ISBN)
                || !(this.pages == other.pages)) {
            return false;
        }
        // Checking authors (length + contents).
        if (this.authors.length != other.authors.length) {
            return false;
        }
        for (int i = 0; i < this.authors.length; i++) {
            if (!this.authors[i].equals(other.authors[i])) {
                return false;
            }
        }
        return true;
    }

    /**
     * Generates a hash code based on all the fields of this BookEntry instance.
     * 
     * @return The hash code corresponding to this specific instance.
     */
    @Override
    public int hashCode() {
        // We can't just hash the authors array, we need to make them into one object.
        // In this case we just concatinate all the fields.
        final StringBuilder sb = new StringBuilder();
        for (final String author : this.authors) {
            sb.append(author);
        }
        return Objects.hash(this.title, sb.toString(), this.rating, this.ISBN, this.pages);
    }
}
