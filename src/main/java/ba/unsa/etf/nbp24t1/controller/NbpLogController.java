package ba.unsa.etf.nbp24t1.controller;

import ba.unsa.etf.nbp24t1.service.NbpLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/nbpLogs")
@RestController
public class NbpLogController {

    private final NbpLogService nbpLogService;
}
