package ba.unsa.etf.nbp24t1.controller;

import ba.unsa.etf.nbp24t1.service.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/genres")
@RestController
public class GenreController {

    private final GenreService genreService;
}
