package ba.unsa.etf.nbp24t1.controller;

import ba.unsa.etf.nbp24t1.service.MovieReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/movieReview")
@RestController
public class MovieReviewController {

    private final MovieReviewService movieReviewService;
}
