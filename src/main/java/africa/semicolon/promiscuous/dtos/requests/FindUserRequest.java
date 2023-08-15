package africa.semicolon.promiscuous.dtos.requests;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class FindUserRequest {
    private long id;
    private int page;
    private int pageSize;
}
