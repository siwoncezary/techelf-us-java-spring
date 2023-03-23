package pl.us.spring.quizapp.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.us.spring.quizapp.model.Quiz;
@RestController
@RequestMapping("/api/v1/quizzes")
public class QuizController {

    @PostMapping("")
    public Quiz createQuiz(Quiz quiz){

    }
}
