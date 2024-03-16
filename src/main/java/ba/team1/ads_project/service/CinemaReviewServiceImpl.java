package ba.team1.ads_project.service;

import ba.team1.ads_project.repository.CinemaReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CinemaReviewServiceImpl implements CinemaReviewService {

    private final CinemaReviewRepository cinemaReviewRepository;
}
