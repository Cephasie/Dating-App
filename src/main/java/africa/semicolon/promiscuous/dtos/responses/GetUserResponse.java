package africa.semicolon.promiscuous.dtos.responses;

import lombok.*;

@ToString

@Builder
@AllArgsConstructor
@Setter
@Getter

public class GetUserResponse {

    private Long id;
    private String email;
    private String fullName;
    private String address;
    private String phoneNumber;


}
