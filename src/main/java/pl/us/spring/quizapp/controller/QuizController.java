package pl.us.spring.quizapp.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.us.spring.quizapp.dto.QuizDto;
import pl.us.spring.quizapp.mapper.QuizMapper;
import pl.us.spring.quizapp.model.Feedback;
import pl.us.spring.quizapp.model.Quiz;
import pl.us.spring.quizapp.dto.QuizAnswerDto;
import pl.us.spring.quizapp.repository.QuizRepositoryInMemory;
import pl.us.spring.quizapp.service.QuizService;

import java.net.URI;
import java.util.*;
@RestController
@RequestMapping("/api/v1/quizzes/")
public class QuizController {
    private final QuizService quizService;

    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @PostMapping("")
    public ResponseEntity<Quiz> createQuiz(@RequestBody Quiz quiz){
        final Quiz saved = quizService.saveQuiz(quiz);
        return ResponseEntity
                .created(URI.create("/api/v1/quizzes/" + saved.getId()))
                .body(saved);
    }

    @GetMapping("")
    public List<QuizDto> getQuizzes(){
        return quizService
                .findAllQuizzes()
                .stream()
                .map(QuizMapper::mapToDto)
                .toList();
    }

    @GetMapping("{id}")
    public ResponseEntity<QuizDto> getQuiz(@PathVariable long id){
        return ResponseEntity.of(quizService.findQuizById(id).map(QuizMapper::mapToDto));
    }



    @PostMapping("{quizId}/answers")
    public boolean getQuizAnswerFeedback(@PathVariable long quizId, @RequestBody QuizAnswerDto answer){
        return quizService.getFeedbackForAnswer(answer);
    }

    @PostMapping("{quizId}/feedback")
    public ResponseEntity<Feedback> getQuizFeedback(
            @PathVariable long quizId,
            @RequestBody QuizAnswerDto answer){
        if (quizId != answer.getQuizId()){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(quizService.getFeebackForAnswerByUser(answer));
    }
}
