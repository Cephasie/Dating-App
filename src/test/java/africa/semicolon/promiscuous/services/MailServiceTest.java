package africa.semicolon.promiscuous.services;

import africa.semicolon.promiscuous.dtos.requests.EmailNotificationRequest;
import africa.semicolon.promiscuous.dtos.requests.Recipient;
import africa.semicolon.promiscuous.dtos.requests.Sender;
import africa.semicolon.promiscuous.dtos.responses.EmailNotificationResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class MailServiceTest {

    @Autowired
    private MailService mailService;

    @Test
    public void testThatSendingMailWorks(){
        String recipientEmail = "hembacephas@gmail.com";
        String message = "<p>testing our mail service</p>";
        String mailSender = "noreply@promiscuous.com";
        String subject = "test email";

        Recipient recipient = new Recipient();
        recipient.setEmail(recipientEmail);
        List<Recipient> recipients = new ArrayList<>();
        recipients.add(recipient);

        Sender sender = new Sender();
        sender.setEmail(mailSender);

        EmailNotificationRequest request = new EmailNotificationRequest();
        request.setMailContent(message);
        request.setRecipients(recipients);
        request.setSubject(subject);
        request.setSender(sender);

        EmailNotificationResponse emailNotificationResponse = mailService.send(request);
        assertNotNull(emailNotificationResponse);
    }
}
