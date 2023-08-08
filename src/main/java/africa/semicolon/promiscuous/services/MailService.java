package africa.semicolon.promiscuous.services;


import africa.semicolon.promiscuous.dtos.requests.EmailNotificationRequest;
import africa.semicolon.promiscuous.dtos.responses.EmailNotificationResponse;

public interface MailService {
    EmailNotificationResponse send(EmailNotificationRequest emailNotificationRequest);

}
