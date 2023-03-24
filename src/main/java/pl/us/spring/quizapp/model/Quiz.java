package pl.us.spring.quizapp.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * Dodaj adnotacje encji nad klasą Quiz,
 * utwórz repozytorium i dodaj dwa quizy w metodzie run
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Quiz implements Serializable {
    private long id;

    private String question;

    private String title;

    transient private List<String> incorrectOptions;

    transient private List<String> correctOptions;
}
