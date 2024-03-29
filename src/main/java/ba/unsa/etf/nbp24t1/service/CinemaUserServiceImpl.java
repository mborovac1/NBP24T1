package ba.unsa.etf.nbp24t1.service;

import ba.unsa.etf.nbp24t1.repository.CinemaUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CinemaUserServiceImpl implements CinemaUserService {

    private final CinemaUserRepository cinemaUserRepository;
}
