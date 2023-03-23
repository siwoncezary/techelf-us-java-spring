package pl.us.spring.quizapp.mapper;

import pl.us.spring.quizapp.dto.QuizDto;
import pl.us.spring.quizapp.model.Quiz;
import java.util.*;
public class QuizMapper {
    public static QuizDto mapToDto(Quiz quiz){
        ArrayList<String> options = new ArrayList<>();
        options.addAll(quiz.getCorrectOptions());
        options.addAll(quiz.getIncorrectOptions());
        return QuizDto
                .builder()
                .Id(quiz.getId())
                .title(quiz.getTitle())
                .question(quiz.getQuestion())
                .options(options)
                .build();
    }
}
