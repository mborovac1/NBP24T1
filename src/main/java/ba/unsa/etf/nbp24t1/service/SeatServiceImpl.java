package ba.unsa.etf.nbp24t1.service;

import ba.unsa.etf.nbp24t1.entity.SeatEntity;
import ba.unsa.etf.nbp24t1.repository.SeatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class SeatServiceImpl implements SeatService {

    private final SeatRepository seatRepository;

    @Override
    public List<SeatEntity> getAll() {
        return seatRepository.findAll();
    }
}
