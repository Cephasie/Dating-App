package africa.semicolon.promiscuous.services;

import africa.semicolon.promiscuous.config.AppConfig;
import africa.semicolon.promiscuous.dtos.requests.EmailNotificationRequest;
import africa.semicolon.promiscuous.dtos.requests.LoginRequest;
import africa.semicolon.promiscuous.dtos.requests.Recipient;
import africa.semicolon.promiscuous.dtos.requests.RegisterUserRequest;
import africa.semicolon.promiscuous.dtos.responses.*;
import africa.semicolon.promiscuous.exceptions.AccountActivationFailedException;
import africa.semicolon.promiscuous.exceptions.UserNotFoundException;
import africa.semicolon.promiscuous.models.Address;
import africa.semicolon.promiscuous.models.User;
import africa.semicolon.promiscuous.repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static africa.semicolon.promiscuous.dtos.responses.ResponseMessage.ACCOUNT_ACTIVATION_SUCCESSFUL;
import static africa.semicolon.promiscuous.dtos.responses.ResponseMessage.USER_REGISTRATION_SUCCESSFUL;
import static africa.semicolon.promiscuous.exceptions.ExceptionMessage.*;
import static africa.semicolon.promiscuous.utils.AppUtils.*;
import static africa.semicolon.promiscuous.utils.JwtUtils.*;

@Service
@AllArgsConstructor
@Slf4j
//simple login facade for jav
public class PromiscuousUserService implements UserService{

    private final UserRepository userRepository;
    private final MailService mailService;
    private final AppConfig appConfig;

    @Override
    public LoginResponse login() {
        return null;
    }

    @Override
    public RegisterUserResponse register(RegisterUserRequest registerUserRequest){
        //1. extract registration details from the registration form
        String email = registerUserRequest.getEmail();
        String password = registerUserRequest.getPassword();

        //2. create a user profile with the registration details
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        user.setAddress(new Address());

        //3. save the users profile in the database
        User savedUser = userRepository.save(user);

        //4. send verification token to the users email
        EmailNotificationRequest request = buildMailRequest(savedUser);
        mailService.send(request);

        //5. return response
        RegisterUserResponse registerUserResponse = new RegisterUserResponse();
        registerUserResponse.setMessage(USER_REGISTRATION_SUCCESSFUL.name());
        return registerUserResponse;
    }

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        String email = loginRequest.getEmail();
        String password = loginRequest.getPassword();

        Optional<User> foundUser = userRepository.findByEmail(email);
        User user = foundUser.orElseThrow(() -> new UserNotFoundException(String.format(USER_WITH_EMAIL_NOT_FOUND_EXCEPTION.getMessage(), email)
        ));
        boolean isValidPassword = matches(user.getPassword(), password);
        if (isValidPassword) {
            String accessToken = generateToken(email);
            LoginResponse loginResponse = new LoginResponse();
            loginResponse.setAccessToken(accessToken);
            return loginResponse;
        }
        throw new BadCredentialsException(INVALID_CREDENTIALS_EXCEPTION)
    }

    @Override
    public ApiResponse<?> activateUserAccount(String token) {
        boolean isTestToken = token.equals(appConfig.getTestToken());
        if(isTestToken) return activateTestAccount();
        boolean isValidJwt = validateToken(token);

        if (isValidJwt) return activateAccount(token);
        throw new AccountActivationFailedException(ACCOUNT_ACTIVATION_FAILED_EXCEPTION.getMessage());
    }
    @Override
    public GetUserResponse getUserById(Long id) {
        Optional<User> found = userRepository.findById(id);
        User user = found.orElseThrow(
                ()-> new UserNotFoundException(USER_NOT_FOUND_EXCEPTION.getMessage())
        );
        GetUserResponse getUserResponse = buildGetUserResponse(user);
        return getUserResponse;
    }

    @Override
    public List<GetUserResponse> getAllUsers(int page, int pageSize) {
        List<GetUserResponse> users = new ArrayList<>();
        Pageable pageable = buildPageRequest(page, pageSize);
        Page<User> usersPage = userRepository.findAll(pageable);
        List<User> foundUsers = usersPage.getContent();

//        for (User user: foundUsers){
//            GetUserResponse getUserResponse = buildGetUserResponse(user);
//            users.add(getUserResponse);
//        }
//        return users
        return foundUsers.stream()
                .map(PromiscuousUserService ::buildGetUserResponse)
                .toList();
    }

    @Override
    public void deleteAll(){

        userRepository.deleteAll();
    }

    private Pageable buildPageRequest(int page, int pageSize) {
        if (page<1 && pageSize<1)return PageRequest.of(0,10);
        if (page<1)return  PageRequest.of(0,pageSize);
        if (pageSize<1)return PageRequest.of(page,pageSize);
        return PageRequest.of(page-1, pageSize);
    }


    private ApiResponse<?> activateAccount(String token) {
        String email = extractEmailFrom(token);
        Optional<User> user = userRepository.findByEmail(email);
        User foundUser = user.orElseThrow(
                ()-> new UserNotFoundException(
                        String.format(USER_WITH_EMAIL_NOT_FOUND_EXCEPTION.getMessage(), email)
                ));

        foundUser.setActive(true);
        User savedUser = userRepository.save(foundUser);
        GetUserResponse userResponse = buildGetUserResponse(savedUser);
        var activateUserResponse = buildActivationUserResponse(userResponse);
        return ApiResponse.builder().data(activateUserResponse).build();


    }

    private static ActivateAccountResponse buildActivationUserResponse(GetUserResponse userResponse){
        return ActivateAccountResponse.builder()
                .message(ACCOUNT_ACTIVATION_SUCCESSFUL.name())
                .user(userResponse)
                .build();
    }

    private static GetUserResponse buildGetUserResponse(User savedUser){
        return GetUserResponse.builder()
                .id(savedUser.getId())
                .address(savedUser.getAddress().toString())
                .fullName(getFullName(savedUser))
                .phoneNumber(savedUser.getPhoneNumber())
                .email(savedUser.getEmail())
                .build();
    }

    private static String getFullName(User savedUser){
        return savedUser.getFirstName() + BLANK_SPACE + savedUser.getLastName();
    }

    private static ApiResponse<?> activateTestAccount() {
        ApiResponse<?> activateAccountResponse =
                ApiResponse.builder()
                        .build();

        return activateAccountResponse;
    }

    private EmailNotificationRequest buildMailRequest(User savedUser){
        EmailNotificationRequest request = new EmailNotificationRequest();
        List<Recipient> recipients = new ArrayList<>();
        Recipient recipient = new Recipient(savedUser.getEmail());
        recipients.add(recipient);
        request.setRecipients(recipients);
//        request.setRecipients(List.of(new Recipient(savedUser.getEmail())));
        request.setSubject(WELCOME_MESSAGE);
        String activationLink = generateActivationLink(appConfig.getBaseUrl(), savedUser.getEmail());
        String emailTemplate = getMailTemplate();
        String mailContent = String.format(emailTemplate, activationLink);
        request.setMailContent(mailContent);
        return request;
    }
}

//        if(token.equals(appConfig.getTestToken())){
//            ApiResponse<?> activateAccountResponse =
//                    ApiResponse
//                            .builder()
//                            .data(new ActivateAccountResponse("Account activation successfully"))
//                            .build();
//            return activateAccountResponse;
//        }
//        if(validateToken(token)){
//            String email = extractEmailFrom(token);
//            User foundUser = userRepository.readByEmail(email).orElseThrow();
//        }
//        throw new PromiscuousBaseException("Account activation was not successful");
//    }
