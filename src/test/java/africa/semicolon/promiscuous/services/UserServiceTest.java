package africa.semicolon.promiscuous.services;

import africa.semicolon.promiscuous.dtos.requests.RegisterUserRequest;
import africa.semicolon.promiscuous.dtos.responses.ApiResponse;
import africa.semicolon.promiscuous.dtos.responses.RegisterUserResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;
    private final RegisterUserRequest registerUserRequest = new RegisterUserRequest();

    @BeforeEach
    public void setUp(){
        registerUserRequest.setEmail("test@email.com");
        registerUserRequest.setPassword("Password");
    }

    @Test
    public void testThatUserCanRegister(){
        //user fills registration form

        //user submits form by calling register method
        RegisterUserResponse registerUserResponse = userService.register(registerUserRequest);
        assertNotNull(registerUserResponse);
        assertNotNull(registerUserResponse.getMessage());
    }

    @Test
    public void testActivateUserAccount(){
        RegisterUserResponse response = userService.register(registerUserRequest);
        assertNotNull(response);

        ApiResponse<?> activateUserAccountResponse = userService.activateUserAccount("abc1234.erytuol.67t756");
        assertThat(activateUserAccountResponse).isNotNull();
    }
}

//    @Test
//    public void  testThatUserCanRegister(){
//        //user fills registration form
//        RegisterUserRequest registerUserRequest = new RegisterUserRequest();
//        registerUserRequest.setEmail("test@email.com");
//        registerUserRequest.setPassword("Password");
//
//        //user submits form by calling register method
//        RegisterUserResponse registerUserResponse = userService.register(registerUserRequest);
//
//        assertNotNull(registerUserResponse);
//        assertNotNull(registerUserResponse.getMessage());
//    }