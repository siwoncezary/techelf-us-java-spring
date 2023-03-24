package pl.us.spring.quizapp;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.us.spring.quizapp.model.Quiz;
import pl.us.spring.quizapp.model.User;
import pl.us.spring.quizapp.repository.QuizRepository;
import pl.us.spring.quizapp.repository.UserRepository;

@SpringBootApplication
public class QuizAppApplication implements CommandLineRunner {
    private final UserRepository userRepository;
    private final QuizRepository quizRepository;

    public QuizAppApplication(UserRepository userRepository, QuizRepository quizRepository) {
        this.userRepository = userRepository;
        this.quizRepository = quizRepository;
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
        if (!quizRepository.findById(1L).isPresent()) {
            quizRepository.save(
                    Quiz.builder()
                            .id(1L)
                            .title("Dodawnie")
                            .question("2 + 3 = ?")
                            .build()
            );
        }
        if (!quizRepository.findById(2L).isPresent()) {
            quizRepository.save(
                    Quiz.builder()
                            .id(2L)
                            .title("Odejmowanie")
                            .question("8 - 3 = ?")
                            .build()
            );
        }
    }
}
