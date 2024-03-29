package ba.unsa.etf.nbp24t1.controller;

import ba.unsa.etf.nbp24t1.service.MovieGenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/movieGenre")
@RestController
public class MovieGenreController {

    private final MovieGenreService movieGenreService;
}
