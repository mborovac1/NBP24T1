package ba.unsa.etf.nbp24t1.service;

import ba.unsa.etf.nbp24t1.entity.TicketEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TicketService {

    List<TicketEntity> getAll();

    ResponseEntity addTicket(TicketEntity ticket, Long id);

    List<Integer> getBookedSeats(Long hallId, Long appointmentId);
}
