package pl.us.spring.quizapp.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.us.spring.quizapp.dto.QuizDto;
import pl.us.spring.quizapp.mapper.QuizMapper;
import pl.us.spring.quizapp.model.Quiz;
import pl.us.spring.quizapp.model.QuizAnswer;
import pl.us.spring.quizapp.repository.QuizRepositoryInMemory;
import pl.us.spring.quizapp.service.QuizService;

import java.net.URI;
import java.util.*;
@RestController
@RequestMapping("/api/v1/quizzes/")
public class QuizController {
    private final QuizRepositoryInMemory quizRepository;
    private final QuizService quizService;

    public QuizController(QuizRepositoryInMemory quizRepository, QuizService quizService) {
        this.quizRepository = quizRepository;
        this.quizService = quizService;
    }

    @PostMapping("")
    public ResponseEntity<Quiz> createQuiz(@RequestBody Quiz quiz){
        final Quiz saved = quizRepository.save(quiz);
        return ResponseEntity
                .created(URI.create("/api/v1/quizzes/" + saved.getId()))
                .body(saved);
    }

    @GetMapping("")
    public List<QuizDto> getQuizzes(){
        return quizRepository
                .findAll()
                .stream()
                .map(QuizMapper::mapToDto)
                .toList();
    }

    @PostMapping("{quizId}/answers")
    public boolean getQuizAnswerFeedback(@PathVariable long quizId, @RequestBody QuizAnswer answer){
        return quizService.getFeedbackForAnswer(answer);
    }
}
