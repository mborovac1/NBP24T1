package ba.unsa.etf.nbp24t1.repository.auth;

import ba.unsa.etf.nbp24t1.entity.auth.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findById(Long id);

    Optional<User> findByEmail(String email);
}
