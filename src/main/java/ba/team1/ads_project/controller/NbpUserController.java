package ba.team1.ads_project.controller;

import ba.team1.ads_project.service.NbpUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/nbpUser")
@RestController
public class NbpUserController {

    private final NbpUserService nbpUserService;
}
