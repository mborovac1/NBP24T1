package ba.unsa.etf.nbp24t1.service;

import ba.unsa.etf.nbp24t1.repository.CinemaReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CinemaReviewServiceImpl implements CinemaReviewService {

    private final CinemaReviewRepository cinemaReviewRepository;
}
