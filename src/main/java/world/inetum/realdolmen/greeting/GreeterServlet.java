package world.inetum.realdolmen.greeting;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/greeting/*")
public class GreeterServlet extends HttpServlet {

    @EJB
    GreeterBean greeterBean;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String name = req.getParameter("name");
        String greeting = greeterBean.greet(name);
        resp.getWriter()
            .println(greeting);
    }
}
