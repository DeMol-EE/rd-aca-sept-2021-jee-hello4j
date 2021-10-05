package world.inetum.realdolmen.greeting;

import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class GreeterBean {

    @EJB
    NameCleanerBean nameCleanerBean;
    @EJB
    PoliteBean politeBean;

    public String greet(String name) {
        String cleaned = nameCleanerBean.clean(name);
        if (cleaned == null) {
            cleaned = "world";
        }
        return politeBean.greet(cleaned);
    }
}
