package ba.unsa.etf.nbp24t1.controller;

import ba.unsa.etf.nbp24t1.entity.HallEntity;
import ba.unsa.etf.nbp24t1.service.HallService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/halls")
@RestController
public class HallController {

    private final HallService hallService;

    @GetMapping("/")
    public List<HallEntity> getAll() {
        return hallService.getAll();
    }
}
