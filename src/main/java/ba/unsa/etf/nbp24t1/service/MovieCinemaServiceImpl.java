package ba.unsa.etf.nbp24t1.service;

import ba.unsa.etf.nbp24t1.repository.MovieCinemaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MovieCinemaServiceImpl implements MovieCinemaService {

    private final MovieCinemaRepository movieCinemaRepository;
}
