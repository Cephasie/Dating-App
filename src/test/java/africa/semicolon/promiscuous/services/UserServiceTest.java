package africa.semicolon.promiscuous.services;

import africa.semicolon.promiscuous.dtos.requests.RegisterUserRequest;
import africa.semicolon.promiscuous.dtos.responses.RegisterUserResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void  testThatUserCanRegister(){
        //user fills registration form
        RegisterUserRequest registerUserRequest = new RegisterUserRequest();
        registerUserRequest.setEmail("testMail@email.com");
        registerUserRequest.setPassword("TestPassword");

        //user submits form by calling register method
        RegisterUserResponse registerUserResponse = userService.register(registerUserRequest);

        assertNotNull(registerUserResponse);
        assertNotNull(registerUserResponse.getMessage());
    }


}
