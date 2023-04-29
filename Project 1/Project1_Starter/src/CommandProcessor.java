/**
 * The purpose of this class is to parse a text file into its appropriate, line
 * by line commands for the format specified in the project specifications.
 * 
 * @author CS group 2
 * 
 * @version 03/2023
 */
public class CommandProcessor {

	// the database object to manipulate the
	// commands that the command processor
	// feeds to it
	private Database data;

	/**
	 * The constructor for the command processor requires a database instance to
	 * exist, so the only constructor takes a database class object to feed commands
	 * to.
	 * 
	 * @param dataIn the database object to manipulate
	 */
	public CommandProcessor() {
		data = new Database();
	}

	/**
	 * This method identifies keywords in the line and calls methods in the database
	 * as required. Each line command will be specified by one of the keywords to
	 * perform the actions within the database required. These actions are performed
	 * on specified objects and include insert, remove, regionsearch, search,
	 * intersections, and dump. If the command in the file line is not one of these,
	 * an appropriate message will be written in the console. This processor method
	 * is called for each line in the file. Note that the methods called will
	 * themselves write to the console, this method does not, only calling methods
	 * that do.
	 * 
	 * @param line a single line from the text file
	 */
	public void processor(String line) {
		try {
			// Split the string into substrings separated by one or more space
			String[] words = line.split("\\s+");
			String operation;
			operation = words[0];
			if (operation.equals("insert")) {
				// Store rectangl's information
				String rectangleName = words[1];
				int upperLeft_x = Integer.parseInt(words[2]);
				int upperLeft_y = Integer.parseInt(words[3]);
				int width = Integer.parseInt(words[4]);
				int height = Integer.parseInt(words[5]);
				// initialize the rectangle
				CustomRectangle rec = new CustomRectangle(upperLeft_x, upperLeft_y, width, height);
				KVPair<String, CustomRectangle> pair = new KVPair<String, CustomRectangle>(rectangleName, rec);
				// call insert method
				data.insert(pair);
			} else if (operation.equals("regionsearch")) {
				// Store rectangl's information
				int upperLeft_x = Integer.parseInt(words[1]);
				int upperLeft_y = Integer.parseInt(words[2]);
				int width = Integer.parseInt(words[3]);
				int height = Integer.parseInt(words[4]);
				// call regionSearch method
				data.regionsearch(upperLeft_x, upperLeft_y, width, height);
			} else if (operation.equals("remove")) {
				// check if that remove by key or value
				if (words.length == 2) // remove by key
				{
					String rectangleName = words[1];
					// call remove method
					data.remove(rectangleName);
				} else // remove by value
				{
					int upperLeft_x = Integer.parseInt(words[1]);
					int upperLeft_y = Integer.parseInt(words[2]);
					int width = Integer.parseInt(words[3]);
					int height = Integer.parseInt(words[4]);
					// call remove method
					data.remove(upperLeft_x, upperLeft_y, width, height);
				}
			} else if (operation.equals("search")) {
				String rectangleName = words[1];
				// call search method
				data.search(rectangleName);
			} else if (operation.equals("intersections")) {
				// call intersection method
				data.intersections();
			} else if (operation.equals("dump")) {
				// call dump method
				data.dump();
			} else {
				System.out.println("Command syntax error!\n");
				System.out.println("The syntax of the command should be:");
				System.out.println("Command name {command parameters}\n");
				System.out.println("Valid commands:");
				System.out.println("insert {name} {x} {y} {w} {h}");
				System.out.println("remove {name}");
				System.out.println("remove {x} {y} {w} {h}");
				System.out.println("regionsearch {x} {y} {w} {h}");
				System.out.println("intersections");
				System.out.println("search {name}");
				System.out.println("dump");
				System.out.println("terms in { } are parameters to the command");
			}
		} catch (NumberFormatException e) {
			System.out.println("Error!:\n" + e.getMessage());
		} catch (NullPointerException e) {
			System.out.println("Error!:\n" + e.getMessage());
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Error!:\n" + e.getMessage());
		} catch (Exception e) {
			System.out.println("Error!:\n" + e.getMessage());
		}
	}

}
