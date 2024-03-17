package ba.team1.ads_project.service;

import ba.team1.ads_project.repository.NbpLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class NbpLogServiceImpl implements NbpLogService {

    private final NbpLogRepository nbpLogRepository;
}
