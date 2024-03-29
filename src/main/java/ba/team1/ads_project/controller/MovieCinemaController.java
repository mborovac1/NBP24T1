package ba.team1.ads_project.controller;

import ba.team1.ads_project.service.MovieCinemaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/movieCinema")
@RestController
public class MovieCinemaController {

    private final MovieCinemaService movieCinemaService;
}
