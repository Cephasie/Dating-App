package africa.semicolon.promiscuous.dtos.responses;

import lombok.Builder;

@Builder
public class ApiResponse<T> {
    private T data;
}
