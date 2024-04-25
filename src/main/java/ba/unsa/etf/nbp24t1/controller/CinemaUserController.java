package ba.unsa.etf.nbp24t1.controller;

import ba.unsa.etf.nbp24t1.entity.CinemaUserEntity;
import ba.unsa.etf.nbp24t1.service.CinemaUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/cinemaUsers")
@CrossOrigin
@RestController
public class CinemaUserController {

    private final CinemaUserService cinemaUserService;

    @GetMapping("/")
    public List<CinemaUserEntity> getAll() {
        return cinemaUserService.getAll();
    }

    @GetMapping(value = "/user/{email}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getKorisnikByEmail(@PathVariable String email) {
        return cinemaUserService.getKorisnikByEmail(email);
    }




}
