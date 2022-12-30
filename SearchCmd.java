import java.util.List;
import java.util.Objects;

public class SearchCmd extends LibraryCommand {
    private String argument;

    /**
     * Constructor of class SearchCmd.
     * @param singleWordTitle The additional argument entered by the user.
     */
    public SearchCmd(String singleWordTitle) {
        super(CommandType.SEARCH, singleWordTitle);
    }

    /**
     * Print out the information of books according to the search keyword entered by the users.
     * @param data Book data to be considered for command execution.
     * @throws NullPointerException if the given library data is null.
     */
    @Override
    public void execute(LibraryData data) {
        Objects.requireNonNull(data, "Given library data must not be null.");
        List<BookEntry> bookList = data.getBookData();
        boolean isBook = false;
        for (int i = 0; i < bookList.size(); i++) {
            String bookTitle = bookList.get(i).getTitle();
            if (bookTitle.toLowerCase().contains(argument.toLowerCase())) {
                System.out.println(bookTitle);
                isBook = true;
            }
        }
        if (isBook == false) {
            System.out.println("No hits found for search term: " + argument);
        }
    }

    /**
     * Initialise parameter argument.
     * Check if the argument entered by user is valid. Argument should not by empty or contain space.
     * @param argumentInput argument input for this command.
     * @return true if the given argument is valid, false otherwise.
     * @throws NullPointerException if the given argumentInput is null.
     */
    @Override
    protected boolean parseArguments(String argumentInput) {
        Objects.requireNonNull(argumentInput, "Given argument must not be null.");
        argument = argumentInput;
        if (argumentInput != "" && !argumentInput.contains(" ")) {
            return true;
        } else {
            return false;
        }
    }
}
