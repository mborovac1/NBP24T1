package ba.unsa.etf.nbp24t1.controller;

import ba.unsa.etf.nbp24t1.service.HallService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/hall")
@RestController
public class HallController {

    private final HallService hallService;
}
