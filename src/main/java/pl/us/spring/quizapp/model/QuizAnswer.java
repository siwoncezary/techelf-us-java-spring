package pl.us.spring.quizapp.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.*;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuizAnswer {
    private int userId;
    private int quizId;
    private List<String> answer;
}
