import java.awt.Rectangle;
@SuppressWarnings("serial")
public class CustomRectangle extends Rectangle {

	public CustomRectangle(int x, int y, int width, int height) {
        //ToDo
    }
  // override function to make this format 
    @Override
    public String toString() {
    	
    	return "("+ x +"," + y + "," + "width" + "height" + ")";
    }
    
    // isInWBoxRect function check if the rectangle in world box or not
    public boolean isInWBoxRect()
    {
    
    	if (x >= 0 && x+width <= 1024 && y >= 0 && y+height <= 1024) 
    	{
    		return true;
		}
    	else
    	{
    		return false;

		}
    }
    
    // isValidRect function check if height and width are greater than 0 or not
    
    public boolean isValidRect() 
    {
    	if (width>0&&height>0) 
    	{
			return true;
		}
    	else 
    	{
			return false;
		}
    	
    }

}