package ba.unsa.etf.nbp24t1.controller;

import ba.unsa.etf.nbp24t1.entity.TicketEntity;
import ba.unsa.etf.nbp24t1.service.TicketService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/tickets")
@RestController
public class TicketController {

    private final TicketService ticketService;

    @GetMapping("/")
    @Operation(summary = "Get all tickets", security = @SecurityRequirement(name = "bearerAuth"))
    public List<TicketEntity> getAll() {
        return ticketService.getAll();
    }
}
