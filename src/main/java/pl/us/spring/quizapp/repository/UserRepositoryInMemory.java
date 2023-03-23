package pl.us.spring.quizapp.repository;

import org.springframework.stereotype.Component;
import pl.us.spring.quizapp.model.User;

import java.util.*;

@Component()
public class UserRepositoryInMemory {

    private final Map<Long, User> users = new HashMap<>();
    private int index = 0;

    public Optional<User> findById(long id){
        return Optional.ofNullable(users.get(id));
    }

    public List<User> findAll() {
        return new ArrayList<>(users.values());
    }

    public User save(User user) {
        user.setId(++index);
        users.put(user.getId(), user);
        return user;
    }

    public User remove(long id){
        return users.remove(id);
    }

    public User update(User user){
        final Optional<User> optionalQuiz = findById(user.getId());
        if (optionalQuiz.isPresent()){
            return users.put(user.getId(), user);
        } else {
            return null;
        }
    }
}
