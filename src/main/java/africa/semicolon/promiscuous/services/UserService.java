package africa.semicolon.promiscuous.services;

import africa.semicolon.promiscuous.dtos.requests.LoginRequest;
import africa.semicolon.promiscuous.dtos.requests.RegisterUserRequest;
import africa.semicolon.promiscuous.dtos.responses.ApiResponse;
import africa.semicolon.promiscuous.dtos.responses.GetUserResponse;
import africa.semicolon.promiscuous.dtos.responses.LoginResponse;
import africa.semicolon.promiscuous.dtos.responses.RegisterUserResponse;

import java.util.List;

public interface UserService {

    LoginResponse login();

    RegisterUserResponse register(RegisterUserRequest registerUserRequest);

    LoginResponse login(LoginRequest loginRequest);

    ApiResponse<?> activateUserAccount(String token);

    GetUserResponse getUserById(Long id);

    List<GetUserResponse> getAllUsers(int page, int pageSize);

    void deleteAll();
}
