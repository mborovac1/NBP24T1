package ba.unsa.etf.nbp24t1.service;

import ba.unsa.etf.nbp24t1.entity.TicketEntity;
import ba.unsa.etf.nbp24t1.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.*;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;
    private static int counter = 0;

    @Override
    public List<TicketEntity> getAll() {
        return ticketRepository.findAll();
    }

    @Override
    public ResponseEntity addTicket(TicketEntity ticket) {
        List<TicketEntity> existingTickets = getAll();
        int maxTicketNumber = existingTickets.isEmpty() ? 0 : existingTickets.stream()
                .mapToInt(TicketEntity::getTicketNumber)
                .max()
                .orElse(0);

        int nextTicketNumber = maxTicketNumber + 1;
        ticket.setTicketNumber(nextTicketNumber);
        ticketRepository.save(ticket);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Ticket has been added successfully!");
    }

    @Override
    public List<Integer> getBookedSeats(Long hallId, Long appointmentId) {
        return ticketRepository.findBookedSeatsByHallIdAndAppointmentId(hallId, appointmentId);
    }
}
