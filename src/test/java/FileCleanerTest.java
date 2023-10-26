import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.IOException;
import java.io.File;
import java.util.Scanner;

public class FileCleanerTest {
    private Logger logger;
    private Scanner scanner;

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @Before
    public void setUp() {
        // Initialize any required objects or resources before each test
        logger = LogManager.getLogger(FileCleaner.class);
        scanner = new Scanner(System.in);

        // Redirect System.out to capture output
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void tearDown() {
        // Release any resources or clean up after each test
        logger = null;
        scanner.close();

        // Restore System.out
        System.setOut(originalOut);
    }

    @Test
    public void testDisplayHelp() {
        // Create a new FileCleaner instance with logger and scanner
        FileCleaner fileCleaner = new FileCleaner(logger, scanner);

        // Call the displayHelp method and capture its result
        String helpOutput = fileCleaner.displayHelp();

        assertNotNull(helpOutput);

        assertTrue(helpOutput.contains("File Cleaner Help"));
        assertTrue(helpOutput.contains("1. To clean a file, select option 1"));
        assertTrue(helpOutput.contains("3. For additional assistance, please contact support"));
    }

    @Test
    public void testCleanEmailList() {
        // Initialize the logger
        Logger logger = LogManager.getLogger(EmailListCleaner.class);

        // Sample input data
        String inputData = "Sweety,Sudini,sweety@gmail.com\nNeha,Chouhan,neha@gmail.com\nInvalidData\n";

        // Create an instance of EmailListCleaner
        EmailListCleaner emailListCleaner = new EmailListCleaner(logger);

        // Call the cleanEmailList method
        String cleanedData = emailListCleaner.cleanEmailList(inputData);

        // Check if the output is not null
        assertNotNull(cleanedData);

        // Check if the cleaned data contains valid entries
        assertTrue(cleanedData.contains("Sweety,Sudini,sweety@gmail.com\n"));
        assertTrue(cleanedData.contains("Neha,Chouhan,neha@gmail.com\n"));

        // Check if the cleaned data does not contain invalid data
        assertEquals(-1, cleanedData.indexOf("InvalidData"));
    }

    @Test
    public void testWriteOutputFile() throws IOException {
        // Initialize the logger
        Logger logger = LogManager.getLogger(EmailListCleaner.class);

        // Sample cleaned data
        String cleanedData = "Sweety,Sudini,sweety@gmail.com\nNeha,Chouhan,neha@gmail.com\n";

        // Define the output file path
        String outputFilePath = "src/test/resources/output_test.csv";

        // Create an instance of EmailListCleaner
        EmailListCleaner emailListCleaner = new EmailListCleaner(logger);

        // Call the writeOutputFile method
        emailListCleaner.writeOutputFile(outputFilePath, cleanedData);

        // Check if the output file exists
        File outputFile = new File(outputFilePath);
        assertTrue(outputFile.exists());

        // Check if the content of the output file matches the cleaned data
        String fileContent = new Scanner(outputFile).useDelimiter("\\A").next();
        assertEquals(cleanedData, fileContent);

        // Clean up: Delete the output file
        outputFile.delete();
    }
}
