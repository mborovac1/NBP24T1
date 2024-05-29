package ba.unsa.etf.nbp24t1.service;

import ba.unsa.etf.nbp24t1.entity.HallEntity;
import ba.unsa.etf.nbp24t1.repository.HallRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class HallServiceImpl implements HallService {

    private final HallRepository hallRepository;

    @Override
    public List<HallEntity> getAll() {
        return hallRepository.findAll();
    }

    @Override
    public HallEntity getHallByHallNumber(Integer hallNumber) {
        return hallRepository.findByHallNumber(hallNumber);
    }

    @Override
    public HallEntity getHallById(Long hallId) {
        return hallRepository.findById(hallId).orElseThrow();
    }
}
