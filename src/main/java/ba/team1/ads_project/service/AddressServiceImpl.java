package ba.team1.ads_project.service;

import ba.team1.ads_project.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
}
