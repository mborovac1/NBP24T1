package ba.unsa.etf.nbp24t1.controller;

import ba.unsa.etf.nbp24t1.entity.MovieEntity;
import ba.unsa.etf.nbp24t1.entity.TicketEntity;
import ba.unsa.etf.nbp24t1.service.TicketService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/tickets")
@CrossOrigin("*")
@RestController
public class TicketController {

    private final TicketService ticketService;

    @GetMapping("/")
    @Operation(summary = "Get all tickets", security = @SecurityRequirement(name = "bearerAuth"))
    public List<TicketEntity> getAll() {
        return ticketService.getAll();
    }

    @PostMapping("/add/{id}")
    @Operation(summary = "Adding new ticket", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity addTicket(@RequestBody TicketEntity ticket, @PathVariable Long id) {
        return ticketService.addTicket(ticket, id);
    }

    @GetMapping("/bookedSeats")
    public ResponseEntity<List<Integer>> getBookedSeats(@RequestParam Long hallId, @RequestParam Long appointmentId) {
        List<Integer> bookedSeats = ticketService.getBookedSeats(hallId, appointmentId);
        return new ResponseEntity<>(bookedSeats, HttpStatus.OK);
    }

}
