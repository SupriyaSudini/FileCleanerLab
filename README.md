# File Cleaner

The File Cleaner is a Java command-line application that reads an input file containing email data, cleans and validates the data, and writes the cleaned data to an output file.

## Getting Started

These instructions will help you compile and run the application on your local machine.

### Prerequisites

You need to have Java Development Kit (JDK) installed on your machine to compile and run this application.

### Compiling the Code

First, compile the Java code using the following command:


javac FileCleaner.java
Running the Application
Run the application using the following command:


java FileCleaner

You will be prompted to enter the source file (input file) containing email data. Provide the file name, including the extension (e.g., data.csv).

The application will perform the following steps:

Read the data from the input file.
Clean and validate the data, removing duplicate emails and formatting names.
Write the cleaned data to an output file.

##Output

The cleaned data is written to a new file with the format: input_clean.csv. The cleaned file will be located in the src/main/resources/ directory.

##Error Handling

If the input file is not found, an error message will be displayed, and the application will terminate. If any other errors occur during processing, they will be displayed with details.

##Logging

The application uses the Apache Log4j library for logging. By default, log messages are displayed in the console. You can configure log settings in the log4j2.xml file.

##Dependencies

Apache Log4j: A reliable, fast, and flexible logging framework.

Java JDK: Required to compile and run the application.

##Conclusion

This project was created as a part of learning Java and software development.





