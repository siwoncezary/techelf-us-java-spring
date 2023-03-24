package pl.us.spring.quizapp;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.us.spring.quizapp.model.User;
import pl.us.spring.quizapp.repository.UserRepository;

@SpringBootApplication
public class QuizAppApplication implements CommandLineRunner {
    private final UserRepository userRepository;

    public QuizAppApplication(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(QuizAppApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        if (!userRepository.findById(1L).isPresent()) {
            userRepository.save(
                    User.builder()
                            .id(1L)
                            .password("1234")
                            .email("karol@op.pl")
                            .name("karol")
                            .build()
            );
        }
    }
}
