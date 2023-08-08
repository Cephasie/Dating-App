package africa.semicolon.promiscuous.dtos.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class EmailNotificationRequest {
    private Sender sender;

    //to
    @JsonProperty("to")
    private List<Recipient> recipients;

    //cc
    @JsonProperty("cc")
    private List<String> copiedEmails;

    //htmlContent
    @JsonProperty("htmlContent")
    private String mailContent;

    private String textContent;

    private String subject;

}
