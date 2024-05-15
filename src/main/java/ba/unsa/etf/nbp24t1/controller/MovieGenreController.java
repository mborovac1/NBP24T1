package ba.unsa.etf.nbp24t1.controller;

import ba.unsa.etf.nbp24t1.entity.MovieEntity;
import ba.unsa.etf.nbp24t1.entity.MovieGenreEntity;
import ba.unsa.etf.nbp24t1.entity.MovieReviewEntity;
import ba.unsa.etf.nbp24t1.service.MovieGenreService;
import ba.unsa.etf.nbp24t1.service.MovieReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/movieGenres")
@RestController
@CrossOrigin
public class MovieGenreController {

    private final MovieGenreService movieGenreService;

    @GetMapping("/")
    public List<MovieGenreEntity> getAll() {
        return movieGenreService.getAll();
    }

    @PostMapping("/add")
    public ResponseEntity createMovieGenre(@RequestBody  List<MovieGenreEntity> movieGenres) {
        return movieGenreService.createMovieGenre(movieGenres);
    }
}