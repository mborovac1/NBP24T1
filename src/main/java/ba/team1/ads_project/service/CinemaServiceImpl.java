package ba.team1.ads_project.service;

import ba.team1.ads_project.repository.CinemaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CinemaServiceImpl implements CinemaService {

    private final CinemaRepository cinemaRepository;
}
