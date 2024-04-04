package ba.unsa.etf.nbp24t1.controller;

import ba.unsa.etf.nbp24t1.entity.NbpLogEntity;
import ba.unsa.etf.nbp24t1.service.NbpLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/nbpLogs")
@RestController
public class NbpLogController {

    private final NbpLogService nbpLogService;

    @GetMapping("/")
    public List<NbpLogEntity> getAll() {
        return nbpLogService.getAll();
    }
}
