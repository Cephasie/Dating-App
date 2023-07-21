package africa.semicolon.promiscuous.services;

import africa.semicolon.promiscuous.dtos.requests.RegisterUserRequest;
import africa.semicolon.promiscuous.dtos.responses.RegisterUserResponse;
import africa.semicolon.promiscuous.models.User;
import africa.semicolon.promiscuous.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.extern.slf4j.XSlf4j;
import org.springframework.stereotype.Repository;


@Repository
@RequiredArgsConstructor
@Slf4j
public class PromiscuousUserService implements UserService{

    private final UserRepository userRepository;

    @Override
    public RegisterUserResponse register(RegisterUserRequest registerUserRequest) {
        //1. extract registration details from the registration form(registrationUserRequest)
        String email = registerUserRequest.getEmail();
        String password = registerUserRequest.getPassword();
        //2. create a user profile with the registration details
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        //3. save that users profile in the Database
        User savedUser = userRepository.save(user);
        //4. send verification token to the users email
        String emailResponse=MockEmailService.sendEmail(savedUser.getEmail());
        log.info("email sending response->{}", emailResponse);
        //5. return a response
        RegisterUserResponse registerUserResponse = new RegisterUserResponse();
        registerUserResponse.setMessage("Registration successful, check your email inbox for verification");

        return registerUserResponse;
    }
}
