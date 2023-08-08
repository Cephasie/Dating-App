package africa.semicolon.promiscuous.services;

import africa.semicolon.promiscuous.dtos.requests.RegisterUserRequest;
import africa.semicolon.promiscuous.dtos.responses.ApiResponse;
import africa.semicolon.promiscuous.dtos.responses.RegisterUserResponse;

public interface UserService {

    RegisterUserResponse register(RegisterUserRequest registerUserRequest);

    ApiResponse<?> activateUserAccount(String token);
}
