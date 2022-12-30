import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class RemoveCmd extends LibraryCommand {
    private String parameter;
    private String search;

    /**
     * Constructor of class RemoveCmd.
     * @param s The additional argument entered by the user.
     */
    public RemoveCmd(String s) {
        super(CommandType.REMOVE, s);
    }

    /**
     * If the content user ask to remove is contained in the library, remove and print a confirmation.
     * Otherwise notify user removal failed.
     * @param data book data to be considered for command execution.
     */
    @Override
    public void execute(LibraryData data) {
        Objects.requireNonNull(data, "Given library data must not be null.");
        List<BookEntry> bookList = data.getBookData();
        if (parameter.equals("TITLE")) {
            boolean removeSuccess = isRemoveByTitle(bookList);
            if (!removeSuccess) {
                System.out.println(search + ": not found.");
            }
        }//if i need to sub in parse to check i need to change this
        //if parse return false do i need to throw illigel argument?

        if (parameter.equals("AUTHOR")) {
            int numOfBooksRemoved = removeByAuthor(bookList);
            System.out.println(numOfBooksRemoved + " books removed for author: " + search);
        }
    }

    /**
     * When user removes by title, check if the removal success or not.
     * @param bookList argument input for this command.
     * @return true if removal success, otherwise false.
     */
    private boolean isRemoveByTitle(List<BookEntry> bookList) {
        boolean isRemove = false;
        Iterator<BookEntry> iterator = bookList.iterator();
        while (iterator.hasNext()) {
            BookEntry eachBook = iterator.next();
            if (search.equals(eachBook.getTitle())) {//should i make it shorter?
                iterator.remove();
                System.out.println(search + ": removed successfully.");
                isRemove = true;
            }
        }
        return isRemove;
    }

    /**
     * When user removes by author, calculate number of books removed.
     * @param bookList argument input for this command.
     * @return number of books removed.
     */
    private Integer removeByAuthor(List<BookEntry> bookList) {//change name?
        int numOfBooksRemoved = 0;
        Iterator<BookEntry> iterator = bookList.iterator();
        while (iterator.hasNext()) {
            BookEntry eachBook = iterator.next();
            for (String author: eachBook.getAuthors()) {
                if(author.equals(search)) {
                    numOfBooksRemoved += 1;
                    iterator.remove();
                }
            }
        }
        return numOfBooksRemoved;
    }

    /**
     * Initialise "parameter" and "search".
     * Check if argument entered by user is valid.
     * "AUTHOR" or "TITLE" is expected for "parameter". "search" should not be empty.
     * @param argumentInput argument input for this command.
     * @return true if the given argument is valid, otherwise false.
     * @throws NullPointerException if the given argumentInput is null.
     */
    @Override
    protected boolean parseArguments(String argumentInput) {
        Objects.requireNonNull(argumentInput, "Given argument must not be null.");
        String[] toBeRemoved = argumentInput.split(" ");
        parameter = toBeRemoved[0];
        search = argumentInput.replace(parameter + " ", "");
        if (isParameterValid() && isSearchValid()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Check if "parameter" valid or not. "AUTHOR" or "TITLE" is expected.
     * @return true if parameter is valid, otherwise false.
     * @throws NullPointerException if "parameter" is null.
     */
    private boolean isParameterValid() {
        Objects.requireNonNull(parameter,
                "Given argument must not be null, it must be 'AUTHOR' or 'TITLE'.");
        boolean isValid = false;
        if (parameter.equals("AUTHOR") || parameter.equals("TITLE")) {
            isValid = true;
        }
        return isValid;
    }

    /**
     * Check if "search" valid or not. It should not be empty.
     * @return true if "search" is valid, otherwise false.
     * @throws NullPointerException if "search" is null.
     */
    private boolean isSearchValid() {
        Objects.requireNonNull(search,
                "Given argument must not be null, you should enter a valid content to remove.");
        boolean isValid = true;
        if (search.equals("")) {
            isValid = false;
        }
        return isValid;
    }

}
