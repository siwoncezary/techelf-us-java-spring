package pl.us.spring.quizapp.service;

import org.springframework.stereotype.Service;
import pl.us.spring.quizapp.dto.RequestUserDto;
import pl.us.spring.quizapp.model.User;
import pl.us.spring.quizapp.repository.UserRepositoryInMemory;

@Service
public class UserServiceInMemory implements UserService {
    private final UserRepositoryInMemory repository;

    public UserServiceInMemory(UserRepositoryInMemory repository) {
        this.repository = repository;
    }

    @Override
    public User register(RequestUserDto userDto) {

        return repository.save(
                    User.builder()
                        .password(userDto.password)
                        .name(userDto.name)
                        .email(userDto.email)
                        .build()
        );
    }


}
