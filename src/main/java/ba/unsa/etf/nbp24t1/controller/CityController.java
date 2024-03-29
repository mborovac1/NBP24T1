package ba.unsa.etf.nbp24t1.controller;

import ba.unsa.etf.nbp24t1.service.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/city")
@RestController
public class CityController {

    private final CityService cityService;
}
