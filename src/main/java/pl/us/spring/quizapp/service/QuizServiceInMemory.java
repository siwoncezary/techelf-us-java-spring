package pl.us.spring.quizapp.service;

import org.springframework.stereotype.Service;
import pl.us.spring.quizapp.model.Feedback;
import pl.us.spring.quizapp.model.Quiz;
import pl.us.spring.quizapp.model.QuizAnswer;
import pl.us.spring.quizapp.repository.QuizRepositoryInMemory;

import java.util.Objects;
import java.util.Optional;
import pl.us.spring.quizapp.model.User;
@Service
public class QuizServiceInMemory implements QuizService{
    private final QuizRepositoryInMemory quizRepository;

    private User currentUser = User
            .builder()
            .id(1)
            .name("Karol")
            .password("1234")
            .build();

    public QuizServiceInMemory(QuizRepositoryInMemory quizRepository) {
        this.quizRepository = quizRepository;
    }

    @Override
    public boolean getFeedbackForAnswer(QuizAnswer answer) {
        final Optional<Quiz> optionalQuiz = quizRepository.findById(answer.getQuizId());
        if (optionalQuiz.isEmpty()){
            return false;
        }
        var quiz = optionalQuiz.get();
        for(var userAnswer: answer.getAnswer()){
            if (!quiz.getCorrectOptions().contains(userAnswer)){
                return false;
            }
        }
        return quiz.getCorrectOptions().size() == answer.getAnswer().size();
    }

    @Override
    public Feedback getFeebackForAnswerByUser(QuizAnswer answer) {
        final Optional<Quiz> optionalQuiz = quizRepository.findById(answer.getQuizId());
        if (optionalQuiz.isEmpty()){
            return Feedback.builder()
                    .percent(0)
                    .message("Quiz with id is not found!")
                    .build();
        }
        var quiz = optionalQuiz.get();
        if (answer.getUserId()  != currentUser.getId()){
            return Feedback.builder()
                    .percent(0)
                    .message("Unknown id user!")
                    .build();
        }
        int points = 0;
        for(var userAnswer: answer.getAnswer()){
            if (quiz.getCorrectOptions().contains(userAnswer)){
                points++;
            }
        }
        return Feedback
                .builder()
                .percent(points/(double) quiz.getCorrectOptions().size())
                .message(points > 99 ?"Success!":"Fail")
                .build();
    }
}
