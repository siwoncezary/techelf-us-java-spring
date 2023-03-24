package pl.us.spring.quizapp.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * Dodaj adnotacje encji nad klasą Quiz,
 * utwórz repozytorium i dodaj dwa quizy w metodzie run
 */

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "quizzes")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Quiz implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Getter
    @Setter
    private long id;

    @Getter
    @Setter
    private String question;

    @Getter
    @Setter
    private String title;

    @Column(length = 5000)
    private String incorrectOptionsAsString;

    @Column(length = 2000)
    private String correctOptionsAsString;

    transient private List<String> incorrectOptions;

    transient private List<String> correctOptions;

    public void setIncorrectOptions(List<String> options){
        incorrectOptionsAsString = String.join("|",options);
    }

    public void setCorrectOptions(List<String> options){
        correctOptionsAsString = String.join("|",options);
    }

    public List<String> getIncorrectOptions(){
        return Arrays.stream(incorrectOptionsAsString.split("\\|")).toList();
    }

    public List<String> getCorrectOptions(){
        return Arrays.stream(correctOptionsAsString.split("\\|")).toList();
    }
}
