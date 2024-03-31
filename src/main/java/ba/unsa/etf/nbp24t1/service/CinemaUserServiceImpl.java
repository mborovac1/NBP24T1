package ba.unsa.etf.nbp24t1.service;

import ba.unsa.etf.nbp24t1.entity.CinemaUserEntity;
import ba.unsa.etf.nbp24t1.repository.CinemaUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CinemaUserServiceImpl implements CinemaUserService {

    private final CinemaUserRepository cinemaUserRepository;

    @Override
    public List<CinemaUserEntity> getAll() {
        return cinemaUserRepository.findAll();
    }
}
