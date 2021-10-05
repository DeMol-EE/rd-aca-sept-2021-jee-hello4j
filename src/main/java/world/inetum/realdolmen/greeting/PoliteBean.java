package world.inetum.realdolmen.greeting;

import javax.ejb.Stateless;

@Stateless
public class PoliteBean {

    public String greet(String input) {
        if (input == null || input.isBlank()) {
            return null;
        }
        return "Hello, " + input +"!";
    }
}
