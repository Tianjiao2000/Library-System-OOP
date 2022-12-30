import java.net.URI;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

public class AddCmd extends LibraryCommand {
    private Path filePath;

    /**
     * Constructor of class AddCmd.
     * @param inputPath The path of file user entered.
     */
    public AddCmd(String inputPath) {
        super(CommandType.ADD, inputPath);
    }

    /**
     * If the given library data is not null then execute it.
     * @param data book data to be considered for command execution.
     * @throws NullPointerException if the given path is null.
     */
    @Override
    public void execute(LibraryData data) {
        Objects.requireNonNull(data, "Given library data must not be null.");
        data.loadData(filePath);
    }

    /**
     * Initialised parameter filePath.
     * Check if argumentInput is valid, a csv file is expected here.
     * @param argumentInput argument input for this command.
     * @return true if the given file in format csv, false otherwise.
     * @throws NullPointerException if the given argumentInput is null.
     */
    @Override
    protected boolean parseArguments(String argumentInput) {
        Objects.requireNonNull(argumentInput, "Given file path must not be null.");
        filePath = Paths.get(argumentInput);
        try {
            String fileEnding = argumentInput.substring(argumentInput.length() - 4);
            if (fileEnding.equals(".csv")) {
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }
}
