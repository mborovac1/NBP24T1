package ba.unsa.etf.nbp24t1.controller;

import ba.unsa.etf.nbp24t1.entity.MembershipEntity;
import ba.unsa.etf.nbp24t1.service.MembershipService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/memberships")
@RestController
public class MembershipController {

    private final MembershipService membershipService;

    @GetMapping("/")
    public List<MembershipEntity> getAll() {
        return membershipService.getAll();
    }
}
