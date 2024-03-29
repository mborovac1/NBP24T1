package ba.team1.ads_project.controller;

import ba.team1.ads_project.service.MovieGenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/movieGenre")
@RestController
public class MovieGenreController {

    private final MovieGenreService movieGenreService;
}
