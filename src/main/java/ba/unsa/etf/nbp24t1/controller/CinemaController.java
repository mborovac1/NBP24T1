package ba.unsa.etf.nbp24t1.controller;

import ba.unsa.etf.nbp24t1.entity.CinemaEntity;
import ba.unsa.etf.nbp24t1.service.CinemaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/cinemas")
@RestController
public class CinemaController {

    private final CinemaService cinemaService;

    @GetMapping("/")
    public List<CinemaEntity> getAll() {
        return cinemaService.getAll();
    }
}
