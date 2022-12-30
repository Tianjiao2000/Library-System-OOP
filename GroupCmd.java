import java.lang.reflect.Array;
import java.util.*;

public class GroupCmd extends LibraryCommand {
    private String argument;

    /**
     * Constructor of class GroupCmd.
     * @param authorArgument The additional argument entered by the user.
     */
    public GroupCmd(String authorArgument) {
        super(CommandType.GROUP, authorArgument);
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
        if (bookList.size() == 0) {
            System.out.println("The library has no book entries.");
        } else {
            if (argument.equals("TITLE")) {
                groupByTitle(bookList);
            } else {
                groupByAuthor(bookList);
            }
        }
    }

    /**
     * Print book information grouped by title.
     * @param bookList book data to be executed.
     */
    private void groupByTitle(List<BookEntry> bookList) {
        System.out.println("Grouped data by TITLE");
        String[] bookTitle = new String[bookList.size()];
        int index = 0;
        for (BookEntry eachBook: bookList) {
            bookTitle[index] = eachBook.getTitle();
            index += 1;
        }
        printByTitle(bookTitle);
    }

    /**
     * Sort array bookTitle to the order ready to be printed.
     * In this case number at first, from A to Z, no matter lower or upper case.
     * @param bookTitle Array of title of books.
     */
    private void sortArray(String[] bookTitle) {
        for (int i = 0; i < bookTitle.length; i ++) {
            for (int j = i; j > 0; j--) {
                if (bookTitle[j - 1].toUpperCase().charAt(0)
                        > bookTitle[j].toUpperCase().charAt(0)) {//large go last
                    String tmp = bookTitle[j - 1];
                    bookTitle[j - 1] = bookTitle[j];
                    bookTitle[j] = tmp;
                }
            }
        }
    }

    /**
     * Print index and each book title when title begin with letter.
     * @param bookTitle Array of title of books.
     */
    private void printByTitle(String[] bookTitle) {
        sortArray(bookTitle);
        char previousFirstLetter = ' ';
        for (int i = 0; i < bookTitle.length; i ++) {
            if (bookTitle[i].charAt(0) >= 'A') {
                if (bookTitle[i].toUpperCase().charAt(0) != previousFirstLetter) {
                    System.out.println("## " + bookTitle[i].toUpperCase().charAt(0));
                    previousFirstLetter = bookTitle[i].toUpperCase().charAt(0);
                }
                System.out.println(bookTitle[i]);
            }
        }
        printByTitleNum(bookTitle);
    }

    /**
     * Print index and each book title when title begin with numbers.
     * @param bookTitle Array of title of books.
     */
    private void printByTitleNum(String[] bookTitle) {
        for (int i = 0; i < bookTitle.length; i ++) {
            if (bookTitle[i].charAt(0) >= '0' && bookTitle[i].charAt(0) <= '9') {
                System.out.println("## [0-9]");
                System.out.println(bookTitle[i]);
            }
        }
    }

    /**
     * Print book information grouped by author.
     * @param bookList book data to be executed.
     */
    private void groupByAuthor(List<BookEntry> bookList) {
        System.out.println("Grouped data by AUTHOR");
        int arrayLength = 0;
        for (BookEntry eachBook: bookList) {
            arrayLength += eachBook.getAuthors().length;
        }
        String[][] authorBook = new String[arrayLength][2];
        int index = 0;
        for (BookEntry eachBook: bookList) {
            for (int j = 0; j < eachBook.getAuthors().length; j++) {
                authorBook[index][0] = eachBook.getAuthors()[j];
                authorBook[index][1] = eachBook.getTitle();
                index += 1;
            }
        }
        Arrays.sort(authorBook, new ColumnComparator(0));
        printByAuthor(authorBook);

    }

    static class ColumnComparator implements Comparator {
        int columnToSort;
        ColumnComparator(int columnToSort) {
            this.columnToSort = columnToSort;
        }
        //overriding compare method
        public int compare(Object o1, Object o2) {
            String[] row1 = (String[]) o1;
            String[] row2 = (String[]) o2;
            //compare the columns to sort
            return row1[columnToSort].compareTo(row2[columnToSort]);
        }
    }

    /**
     * Print index and each author.
     * @param authorBook Array of author of books.
     */
    private void printByAuthor(String[][] authorBook) {
        String previousKey = " ";
        for (int i = 0; i < authorBook.length; i ++) {
            if (!previousKey.equals(authorBook[i][0])) {
                System.out.println("## " + authorBook[i][0]);
                previousKey = authorBook[i][0];
            }
            System.out.println(authorBook[i][1]);
        }

    }

    /**
     * Initialise parameter argument.
     * Check if argument entered by user is valid. "AUTHOR" or "TITLE" is expected here.
     * @param argumentInput argument input for this command.
     * @return true if the given argument is valid, otherwise false.
     * @throws NullPointerException if the given argumentInput is null.
     */
    @Override
    protected boolean parseArguments(String argumentInput) {
        Objects.requireNonNull(argumentInput, "Given argument must not be null.");
        argument = argumentInput;
        if (argumentInput.equals("AUTHOR") || argumentInput.equals("TITLE")) {
            return true;
        } else {
            return false;
        }
    }
}
