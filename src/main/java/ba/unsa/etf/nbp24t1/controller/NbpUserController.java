package ba.unsa.etf.nbp24t1.controller;

import ba.unsa.etf.nbp24t1.service.NbpUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/nbpUsers")
@RestController
public class NbpUserController {

    private final NbpUserService nbpUserService;
}
