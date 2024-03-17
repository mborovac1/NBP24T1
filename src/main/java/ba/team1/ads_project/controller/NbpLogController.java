package ba.team1.ads_project.controller;

import ba.team1.ads_project.service.NbpLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/nbpLog")
@RestController
public class NbpLogController {

    private final NbpLogService nbpLogService;
}
