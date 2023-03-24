package pl.us.spring.quizapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.io.Serializable;
import java.util.List;

/**
 * Dodaj adnotacje encji nad klasą Quiz,
 * utwórz repozytorium i dodaj dwa quizy w metodzie run
 */

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "quizzes")
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Quiz implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private long id;

    private String question;

    private String title;

    transient private List<String> incorrectOptions;

    transient private List<String> correctOptions;
}
