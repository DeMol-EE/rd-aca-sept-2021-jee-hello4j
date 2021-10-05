package world.inetum.realdolmen.email;

import com.icegreen.greenmail.util.GreenMail;
import com.icegreen.greenmail.util.ServerSetup;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.mail.Address;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;

public class EmailBeanTest {
    private GreenMail greenMail;

    @BeforeMethod
    public void setUp() {
        greenMail = new GreenMail(ServerSetup.SMTP);
        greenMail.start();
    }

    @AfterMethod
    public void tearDown() {
        greenMail.stop();
    }

    @Test
    public void testWithDouble() throws MessagingException, IOException {
        EmailBean sut = new EmailBean();
        sut.session = greenMail.getSmtp().createSession();
        sut.emailSender = new EmailSender();
        // act
        sut.sendEmail("test@recipient.com", "This is the body");
        // assert
        boolean gotMail = greenMail.waitForIncomingEmail(1000, 1);
        Assert.assertTrue(gotMail);
        MimeMessage[] receivedMessages = greenMail.getReceivedMessages();
        Assert.assertEquals(receivedMessages.length, 1);
        MimeMessage email = receivedMessages[0];
        Assert.assertEquals(email.getContent().toString(), "This is the body");
        // testing internal detail here... should inject "subject" instead of using a magic value!
        Assert.assertEquals(email.getSubject(), "[JEE]");
        Address[] allRecipients = email.getAllRecipients();
        Assert.assertEquals(allRecipients.length, 1);
        Address recipient = allRecipients[0];
        Assert.assertEquals(recipient.toString(), "test@recipient.com");
    }

    @Test
    public void testWithMocks() throws MessagingException, IOException {
        EmailBean sut = new EmailBean();
        sut.emailSender = Mockito.mock(EmailSender.class);
        // act
        sut.sendEmail("test@recipient.com", "This is the body");
        // assert
        ArgumentCaptor<MimeMessage> mimeMessageArgumentCaptor = ArgumentCaptor.forClass(MimeMessage.class);
        Mockito.verify(sut.emailSender)
                .send(mimeMessageArgumentCaptor.capture());
        MimeMessage email = mimeMessageArgumentCaptor.getValue();
        Assert.assertNotNull(email);
        Assert.assertEquals(email.getContent().toString(), "This is the body");
        Assert.assertEquals(email.getSubject(), "[JEE]");
        Address[] allRecipients = email.getAllRecipients();
        Assert.assertEquals(allRecipients.length, 1);
        Address recipient = allRecipients[0];
        Assert.assertEquals(recipient.toString(), "test@recipient.com");
    }
}