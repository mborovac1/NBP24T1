package ba.unsa.etf.nbp24t1.service;

import ba.unsa.etf.nbp24t1.entity.TicketEntity;
import ba.unsa.etf.nbp24t1.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;

    @Override
    public List<TicketEntity> getAll() {
        return ticketRepository.findAll();
    }
}
