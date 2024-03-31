package ba.unsa.etf.nbp24t1.controller;

import ba.unsa.etf.nbp24t1.entity.CinemaUserEntity;
import ba.unsa.etf.nbp24t1.service.CinemaUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/cinemaUsers")
@RestController
public class CinemaUserController {

    private final CinemaUserService cinemaUserService;

    @GetMapping("/")
    public List<CinemaUserEntity> getAll() {
        return cinemaUserService.getAll();
    }
}
