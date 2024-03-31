package ba.unsa.etf.nbp24t1.service;

import ba.unsa.etf.nbp24t1.entity.CityEntity;
import ba.unsa.etf.nbp24t1.repository.CityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;

    @Override
    public List<CityEntity> getAll() {
        return cityRepository.findAll();
    }
}
