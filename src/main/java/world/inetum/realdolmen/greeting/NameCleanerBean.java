package world.inetum.realdolmen.greeting;

import javax.ejb.Stateless;

@Stateless
public class NameCleanerBean {

    public String clean(String name) {
        if (name == null || name.isBlank()) {
            return null;
        }
        String in = name.trim();
        return in.substring(0, 1)
                 .toUpperCase() + in.substring(1)
                                    .toLowerCase();
    }
}
