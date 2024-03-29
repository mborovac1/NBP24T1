package ba.unsa.etf.nbp24t1.service;

import ba.unsa.etf.nbp24t1.repository.CityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;
}
