package pl.us.spring.quizapp.repository;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import pl.us.spring.quizapp.model.Quiz;
import java.util.*;

@Component
public class QuizRepositoryInMemory {
    private Map<Long, Quiz> data = new HashMap<>();
    private long index = 0;

    public Optional<Quiz> findById(long id){
        if (data.containsKey(id)){
            return Optional.ofNullable(data.get(id));
        } else {
            return Optional.empty();
        }
    }

    public List<Quiz> findAll(){
        return new ArrayList<>(data.values());
    }

    public Quiz save(Quiz quiz){
        quiz.setId(++index);
        data.put(quiz.getId(), quiz);
        return quiz;
    }
}
