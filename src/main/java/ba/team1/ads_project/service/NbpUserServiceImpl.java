package ba.team1.ads_project.service;

import ba.team1.ads_project.repository.NbpUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class NbpUserServiceImpl implements NbpUserService {

    private final NbpUserRepository nbpUserRepository;
}
