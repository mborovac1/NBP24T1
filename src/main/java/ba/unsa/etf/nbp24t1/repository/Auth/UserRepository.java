package ba.unsa.etf.nbp24t1.repository.Auth;

import ba.unsa.etf.nbp24t1.entity.Auth.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findById(int id);
    Optional<User> findByEmail(String email);
}
