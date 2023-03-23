package pl.us.spring.quizapp.controller;

import org.springframework.web.bind.annotation.*;
import pl.us.spring.quizapp.model.Quiz;
import pl.us.spring.quizapp.repository.QuizRepositoryInMemory;
import java.util.*;
@RestController
@RequestMapping("/api/v1/quizzes/")
public class QuizController {
    private final QuizRepositoryInMemory quizRepository;

    public QuizController(QuizRepositoryInMemory quizRepository) {
        this.quizRepository = quizRepository;
    }

    @PostMapping("")
    public Quiz createQuiz(@RequestBody Quiz quiz){
        return quizRepository.save(quiz);
    }

    @GetMapping("")
    public List<Quiz> getQuizzes(){
        return quizRepository.findAll();
    }
}
