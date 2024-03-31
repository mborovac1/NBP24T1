package ba.unsa.etf.nbp24t1.service;

import ba.unsa.etf.nbp24t1.entity.AddressEntity;
import ba.unsa.etf.nbp24t1.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;

    @Override
    public List<AddressEntity> getAll() {
        return addressRepository.findAll();
    }
}
