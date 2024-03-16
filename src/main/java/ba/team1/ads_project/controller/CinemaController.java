package ba.team1.ads_project.controller;

import ba.team1.ads_project.service.CinemaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/cinema")
@RestController
public class CinemaController {

    private final CinemaService cinemaService;
}
