import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ListCmd extends LibraryCommand {
    private String argument;

    /**
     * Constructor of class ListCmd.
     * @param shortArgument The additional argument entered by the user.
     */
    public ListCmd(String shortArgument) {
        super(CommandType.LIST, shortArgument);
    }

    /**
     * Print out details of books according to the command.
     * @param data book data to be considered for command execution.
     * @throws NullPointerException if the given library data is null.
     */
    @Override
    public void execute(LibraryData data) {
        Objects.requireNonNull(data, "Given library data must not be null.");
        List<BookEntry> bookList = data.getBookData();
        int numberOfBooks = bookList.size();
        if (numberOfBooks == 0) {
            System.out.println("The library has no book entries.");
        } else {
            System.out.println(numberOfBooks + " books in library:");
        }

        if (argument.equals("long")) {
            for (int i = 0; i < bookList.size(); i++) {
                System.out.println(bookList.get(i) + "\n");
            }
        } else {
            for (int i = 0; i < bookList.size(); i++) {
                System.out.println(bookList.get(i).getTitle());
            }
        }
    }

    /**
     * Initialise parameter argument.
     * Check if argument entered by user is valid. "short" or "long" is expected here.
     * @param argumentInput argument input for this command.
     * @return true if the given argument is valid, otherwise false.
     * @throws NullPointerException if the given argumentInput is null.
     */
    @Override
    protected boolean parseArguments(String argumentInput) {
        Objects.requireNonNull(argumentInput, "Given argument must not be null.");
        argument = argumentInput;
        if (argumentInput.equals("") || argumentInput.equals("short") || argumentInput.equals("long")) {
            return true;
        } else {
            return false;
        }
    }
}
