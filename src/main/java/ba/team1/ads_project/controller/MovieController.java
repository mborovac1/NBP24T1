package ba.team1.ads_project.controller;

import ba.team1.ads_project.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/movie")
@RestController
public class MovieController {

    private final MovieService movieService;
}
