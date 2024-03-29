package ba.unsa.etf.nbp24t1.service;

import ba.unsa.etf.nbp24t1.repository.CinemaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CinemaServiceImpl implements CinemaService {

    private final CinemaRepository cinemaRepository;
}
