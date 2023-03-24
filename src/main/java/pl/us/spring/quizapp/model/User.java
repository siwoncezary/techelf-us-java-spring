package pl.us.spring.quizapp.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "app_users")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class User implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private long id;

    @Column(nullable = false, length = 100)
    private String email;

    private String name;

    private String password;

    transient private boolean validPassword;
}
