package pl.us.spring.quizapp.repository;

import org.springframework.stereotype.Component;

import java.util.HashMap;

import pl.us.spring.quizapp.model.Quiz;

import java.util.*;

@Component
public class QuizRepositoryInMemory {
    private Map<Long, Quiz> data = new HashMap<>();
    private long index = 0;

    public QuizRepositoryInMemory() {
        data.put(++index, Quiz
                .builder()
                .id(index)
                .title("Dodawanie")
                .correctOptions(List.of("5"))
                .incorrectOptions(List.of("3", "4", "6"))
                .question("2 + 3 = ?")
                .build());
        data.put(++index, Quiz
                .builder()
                .id(index)
                .title("Odejmowanie")
                .correctOptions(List.of("5"))
                .incorrectOptions(List.of("3", "4", "6"))
                .question("8 - 3 = ?")
                .build());

    }

    public Optional<Quiz> findById(long id) {
        if (data.containsKey(id)) {
            return Optional.ofNullable(data.get(id));
        } else {
            return Optional.empty();
        }
    }

    public List<Quiz> findAll() {
        return new ArrayList<>(data.values());
    }

    public Quiz save(Quiz quiz) {
        quiz.setId(++index);
        data.put(quiz.getId(), quiz);
        return quiz;
    }

    public Quiz remove(long id){
        return data.remove(id);
    }

    public Quiz update(Quiz quiz){
        final Optional<Quiz> optionalQuiz = findById(quiz.getId());
        if (optionalQuiz.isPresent()){
            return data.put(quiz.getId(), quiz);
        } else {
            return null;
        }
    }
}
