package pl.us.spring.quizapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.us.spring.quizapp.model.Quiz;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Long> {
}
