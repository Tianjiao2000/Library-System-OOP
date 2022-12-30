import java.util.Arrays;
import java.util.Objects;

/**
 * Immutable class encapsulating data for a single book entry.
 */
public class BookEntry {
    private String title;
    private String[] authors;
    private float rating;
    private String ISBN;
    private int pages;

    /**
     * Change data type of rating from float to Float for next step check the legality.
     * @param newRating The value of rating in type of float.
     * @return value of rating in type of Float.
     */
    private Float setRating(float newRating) {
        return newRating;
    }

    /**
     * Change data type of pages from int to Integer for next step check the legality.
     * @param newPages The value of pages in type of int.
     * @return value of pages in type of Integer.
     */
    private Integer setPages(int newPages) {
        return newPages;
    }

    /**
     * Constructor of BookEntry, set value for instance variables.
     *
     * @param newTitle Value of title that is going to be set as instance variable.
     * @param newAuthor Value of author that is going to be set as instance variable.
     * @param newRating Value of rating that is going to be set as instance variable.
     * @param newISBN Value of ISBN that is going to be set as instance variable.
     * @param newPages Value of pages that is going to be set as instance variable.
     * @throws NullPointerException if the given title is null.
     * @throws NullPointerException if the given array of authors is null
     * @throws NullPointerException if the given rating is null.
     * @throws NullPointerException if the given ISBN is null.
     * @throws NullPointerException if the given pages is null.
     * @throws IllegalArgumentException if the rating is smaller than 0 or greater than 5.
     * @throws IllegalArgumentException if the pages is smaller than 0.
     */
    public BookEntry(String newTitle, String[] newAuthor, float newRating, String newISBN, int newPages) {
        Objects.requireNonNull(newTitle, "The title should not be null!");
        Objects.requireNonNull(newAuthor, "The authors should not be null!");
        Objects.requireNonNull(newISBN, "The ISBN number should not be null!");
        Objects.requireNonNull(setRating(newRating), "The rating should not be null!");
        Objects.requireNonNull(setPages(newPages), "The page number should not be null!");
        title = newTitle;
        authors = newAuthor;
        ISBN = newISBN;

        if (newRating <= 0 || newRating >= 5) {
            throw new IllegalArgumentException("The rating should between 0 and 5.");
        } else {
            rating = newRating;
        }

        if (newPages < 0) {
            throw new IllegalArgumentException("The page number must be positive.");
        } else {
            pages = newPages;
        }
    }

    /**
     * Get value of title.
     * @return value of title.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Get value of author.
     * @return value of author.
     */
    public String[] getAuthors() {
        return authors;
    }

    /**
     * Get value of rating.
     * @return value of rating.
     */
    public float getRating() {
        return rating;
    }

    /**
     * Get value of ISBN.
     * @return value of ISBN.
     */
    public String getISBN() {
        return ISBN;
    }

    /**
     * Get value of pages.
     * @return value of pages.
     */
    public int getPages() {
        return pages;
    }

    /**
     * Return a string representation that contain all information of a book entry.
     * @return The string representation of a book entry.
     */
    @Override
    public String toString() {
        String authorList = "by " + getAuthors()[0];
        for (int i = 1; i < getAuthors().length; i ++) {
            authorList = authorList + ", " + getAuthors()[i];
        }
        return getTitle() + "\n" +
                authorList + "\n" +
                "Rating: " + String.format("%.02f", getRating()) + "\n" +
                "ISBN: " + getISBN() + "\n"
                + getPages() + " pages";
    }

    /**
     * Do some initial instance checking, then compare all five instance fields with the given parameter.
     * @return True if all five instance fields equal to the given parameter. False otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookEntry bookEntry = (BookEntry) o;
        return Float.compare(bookEntry.rating, rating) == 0 &&
                pages == bookEntry.pages &&
                Objects.equals(title, bookEntry.title) &&
                Arrays.equals(authors, bookEntry.authors) &&
                Objects.equals(ISBN, bookEntry.ISBN);
    }

    /**
     * Generate a hash code from all five instance fields.
     * @return The hashcode value of the object.
     */
    @Override
    public int hashCode() {
        int result = Objects.hash(title, rating, ISBN, pages);
        result = 31 * result + Arrays.hashCode(authors);
        return result;
    }
}
