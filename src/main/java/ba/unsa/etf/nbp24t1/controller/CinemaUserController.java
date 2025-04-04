package ba.unsa.etf.nbp24t1.controller;

import ba.unsa.etf.nbp24t1.entity.CinemaUserEntity;
import ba.unsa.etf.nbp24t1.entity.NbpUserEntity;
import ba.unsa.etf.nbp24t1.service.CinemaUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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

    @GetMapping("/users")
    @Operation(summary = "Cinema users", security = @SecurityRequirement(name = "bearerAuth"))
    public List<NbpUserEntity> getAllUsers(){
        return cinemaUserService.getAllUsers();
    }

    @GetMapping(value = "/user/{email}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Users email", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity getKorisnikByEmail(@PathVariable String email) {
        return cinemaUserService.getKorisnikByEmail(email);
    }
}