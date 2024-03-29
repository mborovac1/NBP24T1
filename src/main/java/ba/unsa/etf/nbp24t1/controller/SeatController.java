package ba.unsa.etf.nbp24t1.controller;

import ba.unsa.etf.nbp24t1.service.SeatService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/seat")
@RestController
public class SeatController {

    private final SeatService seatService;
}
