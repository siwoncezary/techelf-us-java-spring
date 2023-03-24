package pl.us.spring.quizapp.service;

import pl.us.spring.quizapp.dto.QuizAnswerDto;
import pl.us.spring.quizapp.model.Feedback;
import pl.us.spring.quizapp.model.Quiz;

import java.util.List;
import java.util.Optional;
public interface QuizService {
    boolean getFeedbackForAnswer(QuizAnswerDto answer);

    List<Quiz> findAllQuizzes();

    Optional<Quiz> findQuizById(long id);

    Feedback getFeebackForAnswerByUser(QuizAnswerDto answer);

    Quiz saveQuiz(Quiz quiz);

    Quiz update(Quiz quiz);

    void remove(long id);
}
