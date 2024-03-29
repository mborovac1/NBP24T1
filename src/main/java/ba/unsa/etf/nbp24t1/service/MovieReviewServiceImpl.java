package ba.unsa.etf.nbp24t1.service;

import ba.unsa.etf.nbp24t1.repository.MovieReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MovieReviewServiceImpl implements MovieReviewService {

    private final MovieReviewRepository movieReviewRepository;
}
