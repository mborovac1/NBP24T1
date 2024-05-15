package ba.unsa.etf.nbp24t1.controller;

import ba.unsa.etf.nbp24t1.entity.MovieEntity;
import ba.unsa.etf.nbp24t1.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/movies")
@RestController
@CrossOrigin
public class MovieController {

    private final MovieService movieService;

    @GetMapping("/")
    public List<MovieEntity> getAll() {
        return movieService.getAll();
    }

    @PostMapping("/add")
    public ResponseEntity addMovie(@RequestBody MovieEntity movie) {
        return movieService.addMovie(movie);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteMovie(@PathVariable int id) {
        return movieService.deleteMovie(id);
    }
}
