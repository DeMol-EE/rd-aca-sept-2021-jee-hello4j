package world.inetum.realdolmen.email;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.mail.MailSessionDefinition;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.validation.constraints.Email;

@Stateless
@MailSessionDefinition(
        name = "java:app/mail/mailhog",
        from = "jee@realdolmen.com",
        // For a full list of SMTP properties, see the javadoc:
        // https://jakarta.ee/specifications/mail/2.0/apidocs/jakarta.mail/jakarta/mail/package-summary.html
        properties = {"mail.smtp.port=1025"}
)
public class EmailBean {

    @Resource(name = "java:app/mail/mailhog")
    Session session;

    @EJB
    EmailSender emailSender;

    public void sendEmail(@Email String to, String body) throws MessagingException {
        MimeMessage email = new MimeMessage(session);
        email.setSubject("[JEE]");
        email.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
        email.setText(body);
        emailSender.send(email);
    }
}
