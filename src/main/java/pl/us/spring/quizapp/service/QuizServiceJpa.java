package pl.us.spring.quizapp.service;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import pl.us.spring.quizapp.dto.QuizAnswerDto;
import pl.us.spring.quizapp.model.Feedback;
import pl.us.spring.quizapp.model.Quiz;
import pl.us.spring.quizapp.model.User;
import pl.us.spring.quizapp.repository.QuizRepository;

import java.util.List;
import java.util.Optional;

@Service
@Primary
public class QuizServiceJpa implements QuizService{
    private final QuizRepository quizRepository;

    public QuizServiceJpa(QuizRepository quizRepository) {
        this.quizRepository = quizRepository;
    }

    @Override
    public boolean getFeedbackForAnswer(QuizAnswerDto answer) {
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
    public List<Quiz> findAllQuizzes() {
        return quizRepository.findAll();
    }

    @Override
    public Optional<Quiz> findQuizById(long id) {
        return quizRepository.findById(id);
    }

    @Override
    public Feedback getFeebackForAnswerByUser(QuizAnswerDto answer) {
        final Optional<Quiz> optionalQuiz = quizRepository.findById(answer.getQuizId());
        if (optionalQuiz.isEmpty()){
            return Feedback.builder()
                    .percent(0)
                    .message("Quiz with id is not found!")
                    .build();
        }
        var quiz = optionalQuiz.get();
        int points = 0;
        for(var userAnswer: answer.getAnswer()){
            if (quiz.getCorrectOptions().contains(userAnswer)){
                points++;
            }
        }
        //TODO popraw logikę, aby ograniczyć nadużycia przy przesyłaniu wielu odpowiedzi
        //TODO liczba odpowiedzi w answer nie powinna być większą od liczby odpowiedzi w quiz
        double percent = 100 * points / (double) quiz.getCorrectOptions().size();
        return Feedback
                .builder()
                .percent(percent)
                .message(percent > 99 ? "Success!":"Fail")
                .build();
    }

    @Override
    public Quiz saveQuiz(Quiz quiz) {
        return quizRepository.save(quiz);
    }
}
