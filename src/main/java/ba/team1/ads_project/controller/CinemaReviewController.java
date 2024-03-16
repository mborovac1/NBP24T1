package ba.team1.ads_project.controller;

import ba.team1.ads_project.service.CinemaReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/cinemaReview")
@RestController
public class CinemaReviewController {

    private final CinemaReviewService cinemaReviewService;
}
