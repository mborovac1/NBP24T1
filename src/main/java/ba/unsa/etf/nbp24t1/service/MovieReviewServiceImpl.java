package ba.unsa.etf.nbp24t1.service;

import ba.unsa.etf.nbp24t1.entity.MovieReviewEntity;
import ba.unsa.etf.nbp24t1.repository.MovieReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class MovieReviewServiceImpl implements MovieReviewService {

    private final MovieReviewRepository movieReviewRepository;

    @Override
    public List<MovieReviewEntity> getAll() {
        return movieReviewRepository.findAll();
    }
}
