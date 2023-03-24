package pl.us.spring.quizapp.controller;
//Zrealizuj kontroler użytkowników:
// - zdefiniuj klasę UserRepositoryInMemory i wstrzyknij do UserController
// - metoda dodania użytkownika
// - metoda pobierania wszystkich użytkowników

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.us.spring.quizapp.dto.RequestUserDto;
import pl.us.spring.quizapp.dto.ResponseUserDto;
import pl.us.spring.quizapp.model.User;
import pl.us.spring.quizapp.service.UserService;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping("")
    public ResponseEntity<ResponseUserDto> registerUser(@Valid @RequestBody RequestUserDto dto){
        final User register = service.register(dto);
        return ResponseEntity.ok(
                ResponseUserDto
                        .builder()
                        .login(register.getName())
                        .userId(register.getId())
                        .build()
        );
    }
}
