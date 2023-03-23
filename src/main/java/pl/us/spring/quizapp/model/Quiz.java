package pl.us.spring.quizapp.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Quiz implements Serializable {
    private long id;

    private String question;

    private String title;

    private List<String> incorrectOptions;

    private List<String> correctOptions;
}
