package ba.unsa.etf.nbp24t1.service;

import ba.unsa.etf.nbp24t1.entity.NbpUserEntity;
import ba.unsa.etf.nbp24t1.model.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface NbpUserService {

    List<NbpUserEntity> getAll();

    NbpUserEntity getById(Long id);

    void add(User user);

    void update(Long id, User user);

    ResponseEntity delete(Long userId);
}
