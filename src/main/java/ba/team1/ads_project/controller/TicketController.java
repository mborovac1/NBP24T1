package ba.team1.ads_project.controller;

import ba.team1.ads_project.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/ticket")
@RestController
public class TicketController {

    private final TicketService ticketService;
}
