package ba.unsa.etf.nbp24t1.controller;

import ba.unsa.etf.nbp24t1.entity.MovieReviewEntity;
import ba.unsa.etf.nbp24t1.service.MovieReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/movieReviews")
@RestController
public class MovieReviewController {

    private final MovieReviewService movieReviewService;

    @GetMapping("/")
    public List<MovieReviewEntity> getAll() {
        return movieReviewService.getAll();
    }
}
