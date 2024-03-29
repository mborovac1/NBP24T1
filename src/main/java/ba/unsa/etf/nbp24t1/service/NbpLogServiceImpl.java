package ba.unsa.etf.nbp24t1.service;

import ba.unsa.etf.nbp24t1.repository.NbpLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class NbpLogServiceImpl implements NbpLogService {

    private final NbpLogRepository nbpLogRepository;
}
