package africa.semicolon.promiscuous.repositories;

import africa.semicolon.promiscuous.models.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void findByEmailTest(){
        userRepository.save(
                User.builder().
                        email("hemba@gmail.com")
                        .password("12345")
                        .firstName("John")
                        .build());

        userRepository.save(
                User.builder().
                        email("test@gmail.com")
                        .password("12345")
                        .firstName("Theo")
                        .build());

        userRepository.save(
                User.builder().
                        email("cephas@gmail.com")
                        .password("12345")
                        .firstName("Cephas")
                        .build());

        Optional<User> foundUser = userRepository.findByEmail("hemba@gmail.com");
        assertThat(foundUser).isPresent();
        assertThat(foundUser.get()).isNotNull();


    }

}
