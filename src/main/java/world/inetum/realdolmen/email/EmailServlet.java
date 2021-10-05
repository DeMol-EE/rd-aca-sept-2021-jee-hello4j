package world.inetum.realdolmen.email;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.mail.MessagingException;
import javax.servlet.annotation.HttpConstraint;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;

@WebServlet(urlPatterns = "/email/*")
@ServletSecurity(@HttpConstraint(value = ServletSecurity.EmptyRoleSemantic.DENY, rolesAllowed = "**"))
public class EmailServlet extends HttpServlet {

    @EJB
    EmailBean emailBean;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String to = req.getParameter("to");
        String body = req.getReader()
                .lines()
                .collect(Collectors.joining());
        try {
            emailBean.sendEmail(to, body);
        } catch (MessagingException e) {
            resp.setStatus(500);
            resp.getWriter().println(e.getMessage());
        } catch (EJBException e) {
            resp.setStatus(400);
            resp.getWriter().println(e.getCausedByException().getMessage());
        }
    }
}
