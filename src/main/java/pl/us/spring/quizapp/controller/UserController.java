package pl.us.spring.quizapp.controller;
//Zrealizuj kontroler użytkowników:
// - zdefiniuj klasę UserRepositoryInMemory i wstrzyknij do UserController
// - metoda dodania użytkownika
// - metoda pobierania wszystkich użytkowników

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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

    public ResponseEntity<ResponseUserDto> registerUser(RequestUserDto dto){
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
