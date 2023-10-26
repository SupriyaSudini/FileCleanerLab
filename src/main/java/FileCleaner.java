import java.io.IOException;
import java.util.Scanner;
import java.io.FileNotFoundException;

// importing logger libraries
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FileCleaner {
	 private Logger logger;
	    private Scanner scanner;

	    // Constructor to accept a logger and scanner
	    public FileCleaner(Logger logger, Scanner scanner) {
	        this.logger = logger;
	        this.scanner = scanner;
	    }

	    public void cleanFile() {
	        System.out.print("Enter the source file name: ");
	        String inputFileName = scanner.nextLine();
	        String inputFilePath = "src/main/resources/" + inputFileName;

	        String outputFileName = inputFileName.replaceAll("\\.(txt|csv)$", "") + "_clean.csv";
	        String outputFilePath = "src/main/resources/" + outputFileName;

	        try {
	            EmailListCleaner cleaner = new EmailListCleaner(logger);
	            String inputData = cleaner.readInputFile(inputFilePath);
	            String cleanedData = cleaner.cleanEmailList(inputData);
	            cleaner.writeOutputFile(outputFilePath, cleanedData);

	            System.out.println("Cleaned file: " + outputFileName);
	            logger.info("The Cleaned File is displayed in Console");
	            System.out.println("Congratulations! Your file has been cleaned.");
	            logger.info("File is Cleaned");
	            System.out.println("\n");
	            
	            // display contents of input file
	            System.out.println("The " + inputFileName + " file");
	            System.out.println(inputData+"...");
	            System.out.println("\n");
	            
	            // display contents of output file 
	            System.out.println("The " + outputFileName + " file");
	            System.out.println(cleanedData+"...");

	        } catch (FileNotFoundException e) {
	            System.err.println("Error: The input file was not found.");
	            logger.error("Input File is not found");
	        } catch (IOException e) {
	            System.err.println("An error occurred while processing the file: " + e.getMessage());
	            logger.error("Error occurred while Processing");
	        }
	    }

	    //method for user Friendly Interface(Help)
	    public String displayHelp() {
	        StringBuilder helpOutput = new StringBuilder();
	        helpOutput.append("File Cleaner Help:\n");
	        helpOutput.append("1. To clean a file, select option 1 and enter the source file name.\n");
	        helpOutput.append("2. To exit the application, select option 3.\n");
	        helpOutput.append("3. For additional assistance, please contact support.");
	        return helpOutput.toString();
	    }

	    // main method
	    public static void main(String[] args) {
	        final Logger logger = LogManager.getLogger(FileCleaner.class);
	        Scanner scanner = new Scanner(System.in);
	        System.out.println("Welcome to File Cleaner");

	        FileCleaner fileCleaner = new FileCleaner(logger, scanner);

	        while (true) {
	            System.out.println("\nMenu:");
	            System.out.println("1. Clean a file");
	            System.out.println("2. Help");
	            System.out.println("3. Exit");
	            System.out.print("Please select an option: ");

	            String input = scanner.nextLine();
	           

	            if (input.equals("1")) {
	                fileCleaner.cleanFile();
	            } else if (input.equals("2")) {
	                System.out.println(fileCleaner.displayHelp());
	            } else if (input.equals("3")) {
	                System.out.println("Thank you for using File Cleaner. Goodbye!");
	                break;
	            } else {
	                System.out.println("Invalid option. Please try again.");
	            }
	        }

	        scanner.close();
	    }

	}