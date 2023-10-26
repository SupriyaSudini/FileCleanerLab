import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.FileNotFoundException;

import org.apache.logging.log4j.Logger;

public class EmailListCleaner {
	
        // to use loggers in this class 
	    private Logger logger;

	    // default constructor without arguments
	    public EmailListCleaner() {}

	    // Constructor with arguments
	    public EmailListCleaner(Logger logger) {
	        this.logger = logger;
	    }
        
	    // method for readingInput File
	    public String readInputFile(String filePath) throws IOException {
	        StringBuilder inputData = new StringBuilder();

	        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
	            String line;
	            while ((line = reader.readLine()) != null) {
	                inputData.append(line).append("\n");
	            }
	        } catch (FileNotFoundException e) {
	            throw new FileNotFoundException("Error: The input file was not found.");
	        } catch (IOException e) {
	            throw new IOException("Error reading the input file: " + e.getMessage());
	        }

	        return inputData.toString();
	    }
	    
	    // method for cleaning Email List
	    public String cleanEmailList(String inputData) {
	        // Split the input data into lines
	    
            String[] lines = inputData.split("\n");

	        StringBuilder cleanedData = new StringBuilder();

	        // Set to store unique email addresses
	        Set<String> uniqueEmails = new HashSet<>();

	        // Regular expression for email validation
	        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
	        Pattern pattern = Pattern.compile(emailRegex);

	        // Process each line
	        for (int i = 0; i < lines.length; i++) {
	            // Split the line into fields
	            String[] fields = lines[i].split(",");
	            
	            if (fields.length != 3) {
	                System.err.println("Data validation error at line " + (i + 1) + ": Invalid number of fields.");
	                logger.error("Data validation error at line " + (i + 1) + ": Invalid number of fields.");
	                continue; // Skip this line
	            }

	            // Ensure title case for names
	            String firstName = fields[0].trim().substring(0, 1).toUpperCase() + fields[0].trim().substring(1).toLowerCase();
	            String lastName = fields[1].trim().substring(0, 1).toUpperCase() + fields[1].trim().substring(1).toLowerCase();
	            String email = fields[2].trim().toLowerCase();

	            // Check for email format validity
	            Matcher matcher = pattern.matcher(email);
	            if (i == 0 || (matcher.matches() && uniqueEmails.add(email))) {
	                // Add the line to cleaned data
	                cleanedData.append(firstName).append(",").append(lastName).append(",").append(email).append("\n");
	            }
	            else {
	                System.err.println("Data validation error at line " + (i + 1) + ": Invalid email format.");
	                logger.error("Data validation error at line " + (i + 1) + ": Invalid email format.");
	            }
	        }

	        return cleanedData.toString();
	    } 
 
	    // method for writing to output file 
	    public void writeOutputFile(String filePath, String cleanedData) throws IOException {
	        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
	            writer.write(cleanedData);
	        } catch (IOException e) {
	            throw new IOException("Error writing the output file: " + e.getMessage());
	        }
	    }

}

