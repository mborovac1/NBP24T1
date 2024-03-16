package ba.team1.ads_project.service;

import ba.team1.ads_project.repository.SeatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SeatServiceImpl implements SeatService {

    private final SeatRepository seatRepository;
}
