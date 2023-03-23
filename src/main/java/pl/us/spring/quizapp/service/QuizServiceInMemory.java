package pl.us.spring.quizapp.service;

import org.springframework.stereotype.Service;
import pl.us.spring.quizapp.model.Quiz;
import pl.us.spring.quizapp.model.QuizAnswer;
import pl.us.spring.quizapp.repository.QuizRepositoryInMemory;

import java.util.Objects;
import java.util.Optional;

@Service
public class QuizServiceInMemory implements QuizService{
    private final QuizRepositoryInMemory quizRepository;

    public QuizServiceInMemory(QuizRepositoryInMemory quizRepository) {
        this.quizRepository = quizRepository;
    }

    @Override
    public boolean getFeedbackForAnswer(QuizAnswer answer) {
        final Optional<Quiz> optionalQuiz = quizRepository.findById(answer.getQuizId());
        if (optionalQuiz.isEmpty()){
            return false;
        }
        if (Objects.equals(answer.getAnswer(), optionalQuiz.get().getCorrectOptions()))
    }
}
