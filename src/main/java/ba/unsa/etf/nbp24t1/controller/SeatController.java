package ba.unsa.etf.nbp24t1.controller;

import ba.unsa.etf.nbp24t1.entity.SeatEntity;
import ba.unsa.etf.nbp24t1.service.SeatService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/seats")
@RestController
public class SeatController {

    private final SeatService seatService;

    @GetMapping("/")
    @Operation(summary = "Get all seats", security = @SecurityRequirement(name = "bearerAuth"))
    public List<SeatEntity> getAll() {
        return seatService.getAll();
    }
}
