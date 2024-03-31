package ba.unsa.etf.nbp24t1.service;

import ba.unsa.etf.nbp24t1.entity.CinemaReviewEntity;
import ba.unsa.etf.nbp24t1.repository.CinemaReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CinemaReviewServiceImpl implements CinemaReviewService {

    private final CinemaReviewRepository cinemaReviewRepository;

    @Override
    public List<CinemaReviewEntity> getAll() {
        return cinemaReviewRepository.findAll();
    }
}
