package ba.unsa.etf.nbp24t1.controller;

import ba.unsa.etf.nbp24t1.entity.CinemaReviewEntity;
import ba.unsa.etf.nbp24t1.service.CinemaReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/cinemaReviews")
@RestController
public class CinemaReviewController {

    private final CinemaReviewService cinemaReviewService;

    @GetMapping("/")
    public List<CinemaReviewEntity> getAll() {
        return cinemaReviewService.getAll();
    }
}
