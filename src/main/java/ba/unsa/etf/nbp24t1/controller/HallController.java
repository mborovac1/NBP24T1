package ba.unsa.etf.nbp24t1.controller;

import ba.unsa.etf.nbp24t1.entity.HallEntity;
import ba.unsa.etf.nbp24t1.service.HallService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/halls")
@RestController
public class HallController {

    private final HallService hallService;

    @GetMapping("/")
    @Operation(summary = "Get all halls", security = @SecurityRequirement(name = "bearerAuth"))
    public List<HallEntity> getAll() {
        return hallService.getAll();
    }
}
