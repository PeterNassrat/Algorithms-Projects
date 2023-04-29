import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * The class containing the main method, the entry point of the application. It
 * will take a command line file argument which include the commands to be read
 * and creates the appropriate SkipList object and outputs the correct results
 * to the console as specified in the file.
 *
 * @author CS group 2
 * 
 * @version 03/2023
 */
public class Rectangle1 {

	/**
	 * The entry point of the application.
	 *
	 * @param args The name of the command file passed in as a command line
	 *             argument.
	 */
	public static void main(String[] args) {
		// the file object
		File file = null;
		// Attempts to open the file and scan through it
		try {
			// takes the first command line argument and opens that file
			file = new File(args[0]);
			// creates a scanner object
			Scanner scanner = new Scanner(file);
			// creates a command processor object
			CommandProcessor cmdProc = new CommandProcessor();
			// reads the entire file and processes the commands
			// line by line
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				// determines if the file has more lines to read
				if (!line.trim().isEmpty()) {
					cmdProc.processor(line.trim());
				}
			}
			// closes the scanner
			scanner.close();
		}
		// catches the exception if the file cannot be found
		// and outputs the correct information to the console
		catch (FileNotFoundException e) {
			System.out.println("Invalid file");
			e.printStackTrace();
		}
	}

}
