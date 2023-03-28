/**
 * This class provides some methods that support the project's needs.
 * 
 * @author CS group 2
 * 
 * @version 03/2023
 *
 */
public class Helper {

	// This method returns a random name of at least 1 letter and at most {size}
	// letters.
	// The first letter of the name is capitalized and the remaining letters are
	// small letters.
	public static String generateRandomName(int size) {
		// Get a random size between 1 and {size}.
		int randomSize = (int) (Math.random() * Integer.MAX_VALUE) % size + 1;
		String randomName = "";
		// Iterate the number of {randomSize} iterations to add random letters.
		for (int i = 0; i < randomSize; i++) {
			// Get a random number that refers to a letter from the 26 alphabet letters.
			int randomNumber = (int) (Math.random() * 100) % 26;
			char character;
			if (i == 0) {
				// Get a random capital alphabet letter using ASCII code representation.
				character = (char) (randomNumber + 65);
			} else {
				// Get a random small alphabet letter using ASCII code representation.
				character = (char) (randomNumber + 97);
			}
			randomName += character; // Add the random letter to the random name.
		}
		return randomName; // return the random name.
	}

	public static boolean isValidName(String name) {
		// Check if the first character is an alphabet letter
		if ((name.charAt(0) >= 'a' && name.charAt(0) <= 'z') || (name.charAt(0) >= 'A' && name.charAt(0) <= 'Z')) {
			// Iterate on the name characters
			for (int i = 1; i < name.length(); i++) {
				// Check if the current character is an alphabet letter or is a digit or an
				// underscore
				if ((name.charAt(i) >= 'a' && name.charAt(i) <= 'z') || (name.charAt(i) >= 'A' && name.charAt(i) <= 'Z')
						|| (name.charAt(i) >= '0' && name.charAt(i) <= '9') || name.charAt(i) == '_') {
					// Do nothing
				} else { // If there exists a character that does not meet the previous condition
					return false;
				}
			}
			// If all characters meet the previous condition
			return true;
		}
		// If the first character does not meet the previous condition
		return false;
	}

	// Put any other needed methods
}
