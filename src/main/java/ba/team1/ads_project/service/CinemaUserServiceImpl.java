package ba.team1.ads_project.service;

import ba.team1.ads_project.repository.CinemaUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CinemaUserServiceImpl implements CinemaUserService {

    private final CinemaUserRepository cinemaUserRepository;
}
