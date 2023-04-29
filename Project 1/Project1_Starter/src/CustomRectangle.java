import java.awt.Rectangle;

/**
 * This class inherits from the Rectangle class to customize it for the project
 * 
 * @author CS group 2
 * 
 * @version 03/2023
 */
@SuppressWarnings("serial")
public class CustomRectangle extends Rectangle {

	public CustomRectangle(int x, int y, int width, int height) {
		super(x, y, width, height);
	}

	// override function to make this format
	@Override
	public String toString() {
		return (this.x + ", " + this.y + ", " + this.width + ", " + this.height);
	}

	// isInWBoxRect function check if the rectangle in world box or not
	public boolean isInWBoxRect() {

		if (this.x >= 0 && this.x + this.width <= 1024 && this.y >= 0 && this.y + this.height <= 1024) {
			return true;
		} else {
			return false;
		}
	}

	// isValidRect function check if height and width are greater than 0 or not
	public boolean isValidRect() {
		if (this.width > 0 && this.height > 0) {
			return true;
		} else {
			return false;
		}

	}

}