package ba.team1.ads_project.service;

import ba.team1.ads_project.repository.NbpRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class NbpRoleServiceImpl implements NbpRoleService {

    private final NbpRoleRepository nbpRoleRepository;
}
