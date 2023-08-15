package africa.semicolon.promiscuous.services;

import africa.semicolon.promiscuous.dtos.requests.RegisterUserRequest;
import africa.semicolon.promiscuous.dtos.responses.ApiResponse;
import africa.semicolon.promiscuous.dtos.responses.GetUserResponse;
import africa.semicolon.promiscuous.dtos.responses.RegisterUserResponse;

import java.util.List;

public interface UserService {

    RegisterUserResponse register(RegisterUserRequest registerUserRequest);

    ApiResponse<?> activateUserAccount(String token);

    GetUserResponse getUserById(Long id);

    List<GetUserResponse> getAllUsers(int page, int pageSize);
}
