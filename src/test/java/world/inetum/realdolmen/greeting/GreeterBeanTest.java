package world.inetum.realdolmen.greeting;

import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GreeterBeanTest {

    @Test
    public void generatesPersonalizedGreeting() {
        GreeterBean sut = new GreeterBean();
        sut.nameCleanerBean = new NameCleanerBean();
        sut.politeBean = new PoliteBean();
        // act
        String greeting = sut.greet("robin");
        // assert
        Assert.assertEquals(greeting, "Hello, Robin!");
    }
}