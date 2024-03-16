package ba.team1.ads_project.service;

import ba.team1.ads_project.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;
}
