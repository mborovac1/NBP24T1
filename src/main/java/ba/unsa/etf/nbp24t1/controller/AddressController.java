package ba.unsa.etf.nbp24t1.controller;

import ba.unsa.etf.nbp24t1.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/addresses")
@RestController
public class AddressController {

    private final AddressService addressService;
}
