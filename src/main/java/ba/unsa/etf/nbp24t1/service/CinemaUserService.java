package ba.unsa.etf.nbp24t1.service;

import ba.unsa.etf.nbp24t1.entity.CinemaUserEntity;
import ba.unsa.etf.nbp24t1.entity.NbpUserEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CinemaUserService {

    List<CinemaUserEntity> getAll();
    List<NbpUserEntity> getAllUsers();
    ResponseEntity getKorisnikByEmail(String email);
    //ResponseEntity getAddressById(int id);

}
