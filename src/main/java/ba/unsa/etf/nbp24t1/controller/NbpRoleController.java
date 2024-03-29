package ba.unsa.etf.nbp24t1.controller;

import ba.unsa.etf.nbp24t1.service.NbpRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/nbpRole")
@RestController
public class NbpRoleController {

    private final NbpRoleService nbpRoleService;
}
