package ba.unsa.etf.nbp24t1.service;

import ba.unsa.etf.nbp24t1.repository.NbpRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class NbpRoleServiceImpl implements NbpRoleService {

    private final NbpRoleRepository nbpRoleRepository;
}
