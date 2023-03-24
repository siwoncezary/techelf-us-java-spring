package pl.us.spring.quizapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;
import pl.us.spring.quizapp.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
