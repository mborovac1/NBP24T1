package ba.team1.ads_project.controller;

import ba.team1.ads_project.service.HallService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/hall")
@RestController
public class HallController {

    private final HallService hallService;
}
