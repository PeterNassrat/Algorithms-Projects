import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({ CustomRectangleTest.class, HelperTest.class, SkipListTest.class })
public class AllTests {

}
