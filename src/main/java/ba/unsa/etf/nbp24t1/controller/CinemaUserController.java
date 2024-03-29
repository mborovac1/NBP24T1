package ba.unsa.etf.nbp24t1.controller;

import ba.unsa.etf.nbp24t1.service.CinemaUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/cinemaUser")
@RestController
public class CinemaUserController {

    private final CinemaUserService cinemaUserService;
}
