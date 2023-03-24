package pl.us.spring.quizapp.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.us.spring.quizapp.model.Quiz;
import pl.us.spring.quizapp.repository.QuizRepositoryInMemory;
import pl.us.spring.quizapp.service.QuizService;

import java.util.Optional;

@RestController
public class QuizAdminController {

    private final QuizService quizService;

    @Autowired
    public QuizAdminController(QuizService quizService) {

        this.quizService = quizService;
    }

    @DeleteMapping("/api/v1/admin/quizzes/{id}")
    public ResponseEntity<Quiz> deleteQuiz(@PathVariable long id){
        quizService.remove(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/api/v1/admin/quizzes/{id}")
    public ResponseEntity<Quiz> updateQuiz(
            @PathVariable long id,
            @RequestBody Quiz quiz
    ){
        if (id != quiz.getId()){
            return ResponseEntity.badRequest().build();
        } else {
            final Quiz update = quizService.update(quiz);
            return update == null
                    ? ResponseEntity.notFound().build()
                    : ResponseEntity.ok(update);
        }
    }

    @PatchMapping(value = "/api/v1/admin/quizzes/{id}", consumes = "application/merge-patch+json")
    public ResponseEntity<Quiz> pathQuiz(@PathVariable long id, @RequestBody JsonPatch pathedQuiz){
        final Optional<Quiz> optionalQuiz = quizService.findQuizById(id);
        if (optionalQuiz.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        Quiz quiz = optionalQuiz.get();
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonQuiz = mapper.convertValue(quiz, JsonNode.class);
        try {
            final JsonNode applied = pathedQuiz.apply(jsonQuiz);
            final Quiz actualQuiz = mapper.treeToValue(applied, Quiz.class);
            quizService.update(actualQuiz);
            return ResponseEntity.ok(actualQuiz);
        } catch (JsonPatchException | JsonProcessingException e) {
            return ResponseEntity.badRequest().build();
        }

    }
}
























