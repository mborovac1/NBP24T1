package ba.team1.ads_project.controller;

import ba.team1.ads_project.service.MembershipService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/membership")
@RestController
public class MembershipController {

    private final MembershipService membershipService;
}
