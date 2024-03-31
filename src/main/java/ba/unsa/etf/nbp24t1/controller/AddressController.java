package ba.unsa.etf.nbp24t1.controller;

import ba.unsa.etf.nbp24t1.entity.AddressEntity;
import ba.unsa.etf.nbp24t1.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/addresses")
@RestController
public class AddressController {

    private final AddressService addressService;

    @GetMapping("/")
    public List<AddressEntity> getAll() {
        return addressService.getAll();
    }
}
