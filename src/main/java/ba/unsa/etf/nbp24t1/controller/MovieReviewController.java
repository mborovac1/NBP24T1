package ba.unsa.etf.nbp24t1.controller;

import ba.unsa.etf.nbp24t1.entity.MovieReviewEntity;
import ba.unsa.etf.nbp24t1.service.MovieReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/movieReviews")
@RestController
@CrossOrigin
public class MovieReviewController {

    private final MovieReviewService movieReviewService;

    @GetMapping("/")
    public List<MovieReviewEntity> getAll() {
        return movieReviewService.getAll();
    }

    @PostMapping("/addMovieReview")
    public ResponseEntity<?> addMovieReview(@RequestBody MovieReviewEntity movieReview) {
        return movieReviewService.addMovieReview(movieReview);
    }
}
