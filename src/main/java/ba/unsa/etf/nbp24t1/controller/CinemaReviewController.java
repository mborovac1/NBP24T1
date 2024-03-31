package ba.unsa.etf.nbp24t1.controller;

import ba.unsa.etf.nbp24t1.service.CinemaReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/cinemaReviews")
@RestController
public class CinemaReviewController {

    private final CinemaReviewService cinemaReviewService;
}
