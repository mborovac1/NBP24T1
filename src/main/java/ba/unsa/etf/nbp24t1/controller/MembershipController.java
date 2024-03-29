package ba.unsa.etf.nbp24t1.controller;

import ba.unsa.etf.nbp24t1.service.MembershipService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/membership")
@RestController
public class MembershipController {

    private final MembershipService membershipService;
}
