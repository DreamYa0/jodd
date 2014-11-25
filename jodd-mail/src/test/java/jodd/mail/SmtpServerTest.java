package jodd.mail;

import org.junit.Test;

import java.util.Properties;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class SmtpServerTest {

    @Test
    public void testAddsPropertyToServerSession() {
        Properties overridenProperties = new Properties();

        overridenProperties.setProperty("mail.smtp.from", "bounce@jodd.org");

        SmtpServer smtpServer = SmtpServer.create("some.host.com", 587)
                .authenticateWith("test", "password")
                .timeout(10)
                .properties(overridenProperties);

        Properties sessionProperties = smtpServer.createSession().mailSession.getProperties();

        assertThat(sessionProperties.getProperty("mail.smtp.from"), is("bounce@jodd.org"));
    }

    @Test
    public void testAddsPropertyToServerSession2() {
        SmtpSslServer smtpServer = SmtpSslServer.create("some.host.com", 587)
                .authenticateWith("test", "password")
                .timeout(10)
                .property("mail.smtp.from", "bounce@jodd.org");

        Properties sessionProperties = smtpServer.createSession().mailSession.getProperties();

        assertThat(sessionProperties.getProperty("mail.smtp.from"), is("bounce@jodd.org"));
    }
}