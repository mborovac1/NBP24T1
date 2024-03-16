package ba.team1.ads_project.service;

import ba.team1.ads_project.repository.MovieReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MovieReviewServiceImpl implements MovieReviewService {

    private final MovieReviewRepository movieReviewRepository;
}
