package ba.unsa.etf.nbp24t1.controller;

import ba.unsa.etf.nbp24t1.service.CinemaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/cinemas")
@RestController
public class CinemaController {

    private final CinemaService cinemaService;
}
