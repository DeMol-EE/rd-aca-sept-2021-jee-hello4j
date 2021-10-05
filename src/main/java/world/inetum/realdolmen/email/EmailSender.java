package world.inetum.realdolmen.email;

import javax.ejb.Asynchronous;
import javax.ejb.Stateless;
import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;
import java.util.logging.Level;
import java.util.logging.Logger;

@Stateless
public class EmailSender {

    static Logger logger = Logger.getLogger(EmailSender.class.getName());

    @Asynchronous
    public void send(MimeMessage email) {
        if (email == null) {
            return;
        }
        try {
            Transport.send(email);
        } catch (MessagingException e) {
            logger.log(Level.INFO, "Failed to send email", e);
        }
    }
}
