package world.inetum.realdolmen.greeting;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import world.inetum.realdolmen.greeting.PoliteBean;

public class PoliteBeanTest {

    @Test
    public void greetsArgument() {
        PoliteBean sut = new PoliteBean();
        String greeting = sut.greet("foo");
        Assert.assertEquals(greeting, "Hello, foo!");
    }

    @Test(dataProvider = "args")
    public void ignoresNullAndBlankStrings(String arg) {
        PoliteBean sut = new PoliteBean();
        String greeting = sut.greet(arg);
        Assert.assertNull(greeting);
    }

    @DataProvider
    public Object[][] args() {
        return new Object[][]{
                {null}, {""}, {"   "}
        };
    }
}