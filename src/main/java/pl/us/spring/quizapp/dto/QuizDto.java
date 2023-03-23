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
public class QuizDto {
    private long Id;
    private String title;
    private String question;
    private List<String> options;
}
