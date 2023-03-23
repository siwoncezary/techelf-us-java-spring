package pl.us.spring.quizapp.service;

import pl.us.spring.quizapp.dto.RequestUserDto;
import pl.us.spring.quizapp.model.User;

public interface UserService {
    User register(RequestUserDto userDto);
}
