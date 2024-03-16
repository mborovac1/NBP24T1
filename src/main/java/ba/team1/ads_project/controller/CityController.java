package ba.team1.ads_project.controller;

import ba.team1.ads_project.service.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/city")
@RestController
public class CityController {

    private final CityService cityService;
}
