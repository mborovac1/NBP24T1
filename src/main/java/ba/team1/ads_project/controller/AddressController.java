package ba.team1.ads_project.controller;

import ba.team1.ads_project.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/address")
@RestController
public class AddressController {

    private final AddressService addressService;
}
