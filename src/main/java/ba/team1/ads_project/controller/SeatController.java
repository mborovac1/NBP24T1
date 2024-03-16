package ba.team1.ads_project.controller;

import ba.team1.ads_project.service.SeatService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/seat")
@RestController
public class SeatController {

    private final SeatService seatService;
}
