package pl.us.spring.quizapp.dto;

import jakarta.validation.constraints.Email;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class RequestUserDto implements Serializable {
    @NonNull
    @Length(min = 2, max = 25, message = "Nazwa użytkownika nie może być krótsza niż 5 znaków i dłuższa od 25 znaków!")
    public String name;

    @Length(min = 4, max = 15)
    public String password;

    @Email
    public String email;
}
