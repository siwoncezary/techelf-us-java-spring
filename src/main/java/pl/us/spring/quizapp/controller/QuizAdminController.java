package pl.us.spring.quizapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.us.spring.quizapp.model.Quiz;
import pl.us.spring.quizapp.repository.QuizRepositoryInMemory;

@RestController
public class QuizAdminController {

    private final QuizRepositoryInMemory quizRepository;

    @Autowired
    public QuizAdminController(QuizRepositoryInMemory quizRepository) {
        this.quizRepository = quizRepository;
    }

    @DeleteMapping("/api/v1/admin/quizzes/{id}")
    public ResponseEntity<Quiz> deleteQuiz(@PathVariable long id){
        var q = quizRepository.remove(id);
        if (q == null){
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(q);
        }
    }

    @PutMapping("/api/v1/admin/quizzes/{id}")
    public ResponseEntity<Quiz> updateQuiz(
            @PathVariable long id,
            @RequestBody Quiz quiz
    ){
        if (id != quiz.getId()){
            return ResponseEntity.badRequest().build();
        } else {
            final Quiz update = quizRepository.update(quiz);
            return update == null
                    ? ResponseEntity.notFound().build()
                    : ResponseEntity.ok(update);
        }
    }
}
