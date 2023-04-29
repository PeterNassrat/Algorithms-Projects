import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.jupiter.api.Test;

/**
 * This class tests the Helper class and ensures that it is well implemented and
 * there aren't any problems with the general use purposes.
 * 
 * @author CS group 2
 *
 * @version 03/2023
 */
class HelperTest {

	// Tests for "generateRandomName" function
	@Test // Test the length of the string
	void randomName_test1() {
		String rand_name = Helper.generateRandomName(5);
		assertTrue(rand_name.length() >= 1 && rand_name.length() <= 5);
	}

	@Test // Test capital and small letters of the string
	void randomName_test2() {
		String rand_name = Helper.generateRandomName(5);
		boolean valid = (rand_name.charAt(0) >= 'A' && rand_name.charAt(0) <= 'Z');

		for (int i = 1; i < rand_name.length(); i++) {
			if (rand_name.charAt(i) >= 'A' && rand_name.charAt(i) <= 'Z')
				valid = false;
		}
		assertTrue(valid);
	}

	// Tests for "isValidName" function
	@Test // Test valid values
	void validName_test1() {

		assertTrue(Helper.isValidName("Name"));
		assertTrue(Helper.isValidName("N4Ame256"));
	}

	@Test // Test invalid values
	void validName_test2() {

		assertFalse(Helper.isValidName("5Name"));
		assertFalse(Helper.isValidName("Name&"));
	}

}
