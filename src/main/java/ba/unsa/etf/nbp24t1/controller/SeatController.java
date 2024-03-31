package ba.unsa.etf.nbp24t1.controller;

import ba.unsa.etf.nbp24t1.entity.SeatEntity;
import ba.unsa.etf.nbp24t1.service.SeatService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/seats")
@RestController
public class SeatController {

    private final SeatService seatService;

    @GetMapping("/")
    public List<SeatEntity> getAll() {
        return seatService.getAll();
    }
}
