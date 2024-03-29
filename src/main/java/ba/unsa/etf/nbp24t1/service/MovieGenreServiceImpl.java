package ba.unsa.etf.nbp24t1.service;

import ba.unsa.etf.nbp24t1.repository.MovieGenreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MovieGenreServiceImpl implements MovieGenreService {

    private final MovieGenreRepository movieGenreRepository;
}
