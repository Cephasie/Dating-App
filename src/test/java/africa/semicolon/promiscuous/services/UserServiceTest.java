package africa.semicolon.promiscuous.services;

import africa.semicolon.promiscuous.dtos.requests.RegisterUserRequest;
import africa.semicolon.promiscuous.dtos.responses.ApiResponse;
import africa.semicolon.promiscuous.dtos.responses.GetUserResponse;
import africa.semicolon.promiscuous.dtos.responses.RegisterUserResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Slf4j
@ActiveProfiles("test")
public class UserServiceTest {

    @Autowired
    private UserService userService;

    private RegisterUserRequest registerUserRequest;

    private RegisterUserResponse registerUserResponse;

    @BeforeEach
    void setUp(){
        registerUserRequest = new RegisterUserRequest();
        registerUserRequest.setEmail("hemba@gmail.com");
        registerUserRequest.setPassword("password");

    }

    @Test
    public void testThatUserCanRegister(){
        // User submit form by calling register method
        registerUserResponse = userService.register(registerUserRequest);

        assertNotNull(registerUserResponse);
        assertNotNull(registerUserResponse.getMessage());
    }

    @Test
    public void testActivateUserAccount(){
        registerUserRequest.setEmail("test@gmail.com");
        registerUserResponse = userService.register(registerUserRequest);
        assertNotNull(registerUserResponse);

        ApiResponse<?> activateUserAccountResponse =
                userService.activateUserAccount("abc1234.erytuuoi.67t75646");

        assertThat(activateUserAccountResponse).isNotNull();

    }

    @Test

    public void getUserByIdTest(){
        userService.register(registerUserRequest);
        GetUserResponse response =userService.getUserById(1L);
        assertThat(response).isNotNull();
        assertThat(response.getEmail()).isEqualTo(registerUserRequest.getEmail());


    }

    @Test
    public void getAllUsers(){
        registerTestUsers();

        List<GetUserResponse> users = userService.getAllUsers(1, 5);
        assertThat(users).isNotNull();
        log.info("users-->{}", users);
        assertThat(users.size()).isEqualTo(5);
    }

    private void registerTestUsers() {
        RegisterUserRequest request = new RegisterUserRequest();
        request.setEmail("hemba@gmail.com");
        request.setPassword("password");
        userService.register(request);

        request.setEmail("boy@gmail.com");
        request.setPassword("password");
        userService.register(request);

        request.setEmail("love@gmail.com");
        request.setPassword("password");
        userService.register(request);

        request.setEmail("hate@gmail.com");
        request.setPassword("password");
        userService.register(request);

        request.setEmail("life@gmail.com");
        request.setPassword("password");
        userService.register(request);

        request.setEmail("freedom@gmail.com");
        request.setPassword("password");
        userService.register(request);
    }
}

//
//    @BeforeEach
//    public void setUp(){
//        registerUserRequest.setEmail("test@email.com");
//        registerUserRequest.setPassword("Password");
//    }
//
//    @Test
//    public void testThatUserCanRegister(){
//        //user fills registration form
//
//        //user submits form by calling register method
//        RegisterUserResponse registerUserResponse = userService.register(registerUserRequest);
//        assertNotNull(registerUserResponse);
//        assertNotNull(registerUserResponse.getMessage());
//    }
//
//    @Test
//    public void testActivateUserAccount(){
//        RegisterUserResponse response = userService.register(registerUserRequest);
//        assertNotNull(response);
//
//        ApiResponse<?> activateUserAccountResponse = userService.activateUserAccount("abc1234.erytuol.67t756");
//        assertThat(activateUserAccountResponse).isNotNull();
//    }
//}

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