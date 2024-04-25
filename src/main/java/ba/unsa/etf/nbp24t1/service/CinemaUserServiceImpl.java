package ba.unsa.etf.nbp24t1.service;

import ba.unsa.etf.nbp24t1.entity.CinemaUserEntity;
import ba.unsa.etf.nbp24t1.entity.NbpUserEntity;
import ba.unsa.etf.nbp24t1.exception.NotFoundException;
import ba.unsa.etf.nbp24t1.repository.AddressRepository;
import ba.unsa.etf.nbp24t1.repository.CinemaUserRepository;
import ba.unsa.etf.nbp24t1.repository.NbpUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CinemaUserServiceImpl implements CinemaUserService {

    private final CinemaUserRepository cinemaUserRepository;
    private final NbpUserRepository nbpUserRepository;
    private final AddressRepository addressRepository;

    @Override
    public List<CinemaUserEntity> getAll() {
        return cinemaUserRepository.findAll();
    }

    public List<NbpUserEntity> getAllUsers() {
        List<CinemaUserEntity> cinemaUsers = cinemaUserRepository.findAll();
        List<NbpUserEntity> finalUsers = new ArrayList<>();

        for (CinemaUserEntity cinemaUser : cinemaUsers) {
            Optional<NbpUserEntity> userOptional = nbpUserRepository.findById(cinemaUser.getUserId());
            userOptional.ifPresent(finalUsers::add);
        }

        return finalUsers;
    }

    @Override
    public ResponseEntity getKorisnikByEmail(String email){
        if(nbpUserRepository.existsByEmail(email)) {
            return new ResponseEntity(nbpUserRepository.findByEmail(email), HttpStatus.OK);
        } else {
            throw new NotFoundException(String.format("User with email %s doesn't exist.", email));
        }
    }



}
