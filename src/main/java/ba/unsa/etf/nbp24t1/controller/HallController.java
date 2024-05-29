package ba.unsa.etf.nbp24t1.controller;

import ba.unsa.etf.nbp24t1.entity.HallEntity;
import ba.unsa.etf.nbp24t1.service.HallService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/halls")
@CrossOrigin
@RestController
public class HallController {

    private final HallService hallService;

    @GetMapping("/")
    @Operation(summary = "Get all halls", security = @SecurityRequirement(name = "bearerAuth"))
    public List<HallEntity> getAll() {
        return hallService.getAll();
    }

    /*@GetMapping("/{hallNumber}")
    @Operation(summary = "Get hall by hall number", security = @SecurityRequirement(name = "bearerAuth"))
    public HallEntity getHallByHallNumber(@PathVariable int hallNumber) {
        return hallService.getHallByHallNumber(hallNumber);
    }*/

    @GetMapping("/{hallId}")
    @Operation(summary = "Get hall by hall id", security = @SecurityRequirement(name = "bearerAuth"))
    public HallEntity getHallById(@PathVariable Long hallId) {
        return hallService.getHallById(hallId);
    }
}
