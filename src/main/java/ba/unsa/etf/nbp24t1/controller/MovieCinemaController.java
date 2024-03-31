package ba.unsa.etf.nbp24t1.controller;

import ba.unsa.etf.nbp24t1.service.MovieCinemaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/movieCinemas")
@RestController
public class MovieCinemaController {

    private final MovieCinemaService movieCinemaService;
}
