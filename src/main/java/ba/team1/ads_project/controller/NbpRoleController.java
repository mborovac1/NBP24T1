package ba.team1.ads_project.controller;

import ba.team1.ads_project.service.NbpRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/nbpRole")
@RestController
public class NbpRoleController {

    private final NbpRoleService nbpRoleService;
}
