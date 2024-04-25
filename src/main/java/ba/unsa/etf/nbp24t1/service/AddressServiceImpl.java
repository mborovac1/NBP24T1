package ba.unsa.etf.nbp24t1.service;

import ba.unsa.etf.nbp24t1.entity.AddressEntity;
import ba.unsa.etf.nbp24t1.exception.NotFoundException;
import ba.unsa.etf.nbp24t1.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @Override
    public ResponseEntity getAddressById(int id){
        if(addressRepository.existsById(id)) {
            return new ResponseEntity(addressRepository.findById(id), HttpStatus.OK);
        } else {
            throw new NotFoundException(String.format("Address with id %s doesn't exist.", id));
        }
    }
}
