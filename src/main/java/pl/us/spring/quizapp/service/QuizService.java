package pl.us.spring.quizapp.service;

import pl.us.spring.quizapp.model.QuizAnswer;

public interface QuizService {
    boolean getFeedbackForAnswer(QuizAnswer answer);
}
