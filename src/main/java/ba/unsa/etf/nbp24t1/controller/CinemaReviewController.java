package ba.unsa.etf.nbp24t1.controller;

import ba.unsa.etf.nbp24t1.entity.CinemaReviewEntity;
import ba.unsa.etf.nbp24t1.entity.MovieEntity;
import ba.unsa.etf.nbp24t1.service.CinemaReviewService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/cinemaReviews")
@RestController
@CrossOrigin
public class CinemaReviewController {

    private final CinemaReviewService cinemaReviewService;

    @GetMapping("/")
    public List<CinemaReviewEntity> getAll() {
        return cinemaReviewService.getAll();
    }

    @PostMapping("/addCinemaReview")
    @Operation(summary = "Cinema review", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity addCinemaReview(@RequestBody CinemaReviewEntity cinemaReview) {
        return cinemaReviewService.addCinemaReview(cinemaReview);
    }
}
