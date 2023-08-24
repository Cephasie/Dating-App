package africa.semicolon.promiscuous.services;

import africa.semicolon.promiscuous.dtos.requests.LoginRequest;
import africa.semicolon.promiscuous.dtos.requests.RegisterUserRequest;
import africa.semicolon.promiscuous.dtos.requests.UpdateUserRequest;
import africa.semicolon.promiscuous.dtos.responses.*;
import africa.semicolon.promiscuous.models.User;
import com.github.fge.jsonpatch.JsonPatch;

import java.util.List;

public interface UserService {
    RegisterUserResponse register(RegisterUserRequest registerUserRequest);

    ApiResponse<?>activateUserAccount(String token);


    GetUserResponse getUserById(Long id);

    List<GetUserResponse> getAllUsers(int page, int pageSize);
    LoginResponse login(LoginRequest loginRequest);

//    UpdateUserResponse updateUserProfile(JsonPatch jsonPatch, Long id);

    UpdateUserResponse updateProfile(UpdateUserRequest updateUserRequest, Long id);

    List<User>suggestUserByInterest(Long userId);
}

//    LoginResponse login();
//
//    RegisterUserResponse register(RegisterUserRequest registerUserRequest);
//
//    LoginResponse login(LoginRequest loginRequest);
//
//    ApiResponse<?> activateUserAccount(String token);
//
//    GetUserResponse getUserById(Long id);
//
//    List<GetUserResponse> getAllUsers(int page, int pageSize);
//
//    void deleteAll();