package ba.unsa.etf.nbp24t1.service;

import ba.unsa.etf.nbp24t1.repository.NbpUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class NbpUserServiceImpl implements NbpUserService {

    private final NbpUserRepository nbpUserRepository;
}
