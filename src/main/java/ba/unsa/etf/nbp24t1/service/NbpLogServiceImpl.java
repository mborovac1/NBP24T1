package ba.unsa.etf.nbp24t1.service;

import ba.unsa.etf.nbp24t1.entity.NbpLogEntity;
import ba.unsa.etf.nbp24t1.repository.NbpLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class NbpLogServiceImpl implements NbpLogService {

    private final NbpLogRepository nbpLogRepository;

    @Override
    public List<NbpLogEntity> getAll() {
        return nbpLogRepository.findAll();
    }
}
