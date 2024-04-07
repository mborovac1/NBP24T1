package ba.unsa.etf.nbp24t1.repository.auth;

import ba.unsa.etf.nbp24t1.entity.auth.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Integer> {

    @Query(value = """
            SELECT t FROM Token t INNER JOIN User u\s
            ON t.user.id = u.id\s
            WHERE u.id = :id AND (t.expired = FALSE OR t.revoked = FALSE)\s
            """)
    List<Token> findAllValidTokenByUser(Integer id);

    Optional<Token> findByToken(String token);
}
