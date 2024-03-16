package ba.team1.ads_project.service;

import ba.team1.ads_project.repository.HallRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class HallServiceImpl implements HallService {

    private final HallRepository hallRepository;
}
