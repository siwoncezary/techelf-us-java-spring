package pl.us.spring.quizapp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.*;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuizAnswerDto {
    private long userId;
    private long quizId;
    private List<String> answer;
}
