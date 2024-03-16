package ba.team1.ads_project.controller;

import ba.team1.ads_project.service.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/genre")
@RestController
public class GenreController {

    private final GenreService genreService;
}
