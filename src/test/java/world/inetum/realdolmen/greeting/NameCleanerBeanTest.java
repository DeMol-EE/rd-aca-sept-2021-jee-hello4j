package world.inetum.realdolmen.greeting;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class NameCleanerBeanTest {

    @Test
    public void cleanCapitalizesFirstLetterAndLowercasesOthers() {
        NameCleanerBean sut = new NameCleanerBean();
        String actual = sut.clean("roBiN");
        Assert.assertEquals(actual, "Robin");
    }

    @Test(dataProvider = "data")
    public void cleanIgnoresNullAndBlankStrings(String name) {
        NameCleanerBean sut = new NameCleanerBean();
        String actual = sut.clean(name);
        Assert.assertNull(actual);
    }

    @DataProvider
    public Object[][] data() {
        return new Object[][]{{null}, {""}, {"  "}};
    }
}