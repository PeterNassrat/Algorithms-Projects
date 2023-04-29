import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.jupiter.api.Test;

/**
 * This class tests the CustomRectangle class and ensures that it is well
 * implemented and there aren't any problems with it, and it is safe for use in
 * the project.
 * 
 * @author CS group 2
 *
 * @version 03/2023
 */
class CustomRectangleTest {

	CustomRectangle rect;

	@Test
	public void test01() {
		// To test the validation of rectangle and if it within the "world box"
		rect = new CustomRectangle(0, 0, 1024, 1024);
		assertTrue(rect.isInWBoxRect());
		assertTrue(rect.isValidRect());
	}

	@Test
	public void test02() {
		// To test the validation of rectangle and if it within the "world box"
		rect = new CustomRectangle(1, 0, 2, 4);
		assertTrue(rect.isInWBoxRect());
		assertTrue(rect.isValidRect());
	}

	@Test
	public void test03() {
		// To test that rectangle has upper left corner at (0, 0) or greater
		rect = new CustomRectangle(-1, -1, 2, 3);
		assertFalse(rect.isInWBoxRect());
		assertTrue(rect.isValidRect());
	}

	@Test
	public void test04() {
		// To test that width within 1024
		rect = new CustomRectangle(20, 10, 1020, 1000);
		assertFalse(rect.isInWBoxRect());
		assertTrue(rect.isValidRect());
	}

	@Test
	public void test05() {
		// To test that width within 1024
		rect = new CustomRectangle(0, 0, 1026, 1000);
		assertFalse(rect.isInWBoxRect());
		assertTrue(rect.isValidRect());
	}

	@Test
	public void test06() {
		// To test that height within 1024
		rect = new CustomRectangle(4, 8, 1020, 1020);
		assertFalse(rect.isInWBoxRect());
		assertTrue(rect.isValidRect());
	}

	@Test
	public void test07() {
		// To test that height within 1024
		rect = new CustomRectangle(4, 0, 1020, 1050);
		assertFalse(rect.isInWBoxRect());
		assertTrue(rect.isValidRect());
	}

	@Test
	public void test08() {
		// To test that width and height are greater than 0
		rect = new CustomRectangle(2, 4, 0, 0);
		assertFalse(rect.isValidRect());
	}

	@Test
	public void test09() {
		// To test that width and height are greater than 0
		rect = new CustomRectangle(5, 5, -1, -1);
		assertFalse(rect.isValidRect());
	}

	@Test
	public void test10() {
		// To test the overridden toString method
		rect = new CustomRectangle(134, 122, 22, 46);
		String rectString = "134, 122, 22, 46";
		assertEquals(rectString, rect.toString());
	}
	
	@Test
	public void test11() {
		// To test the intersects method
		rect = new CustomRectangle(0, 0, 5, 5);
		assertFalse(rect.intersects(new CustomRectangle(5, 5, 5, 5)));
	}
	
	@Test
	public void test12() {
		// To test the intersects method
		rect = new CustomRectangle(0, 0, 5, 5);
		assertFalse(rect.intersects(new CustomRectangle(0, 5, 5, 5)));
	}
	
	@Test
	public void test13() {
		// To test the intersects method
		rect = new CustomRectangle(0, 0, 5, 5);
		assertFalse(rect.intersects(new CustomRectangle(5, 0, 5, 5)));
	}
	
	@Test
	public void test14() {
		// To test the intersects method
		rect = new CustomRectangle(5, 5, 5, 5);
		assertTrue(rect.intersects(new CustomRectangle(0, 0, 10, 10)));
	}
	
	@Test
	public void test15() {
		// To test the intersects method
		rect = new CustomRectangle(5, 5, 5, 5);
		assertTrue(rect.intersects(new CustomRectangle(0, 0, 15, 15)));
	}
}
