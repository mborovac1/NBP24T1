package ba.team1.ads_project.service;

import ba.team1.ads_project.repository.CityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;
}
