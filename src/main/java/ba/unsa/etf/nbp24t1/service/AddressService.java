package ba.unsa.etf.nbp24t1.service;

import ba.unsa.etf.nbp24t1.entity.AddressEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AddressService {

    List<AddressEntity> getAll();
    ResponseEntity getAddressById(int id);
}
