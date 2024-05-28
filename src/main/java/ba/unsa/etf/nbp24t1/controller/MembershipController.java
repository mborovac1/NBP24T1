package ba.unsa.etf.nbp24t1.controller;

import ba.unsa.etf.nbp24t1.entity.MembershipEntity;
import ba.unsa.etf.nbp24t1.service.MembershipService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/memberships")
@RestController
@CrossOrigin
public class MembershipController {

    private final MembershipService membershipService;

    @GetMapping("/")
    public List<MembershipEntity> getAll() {
        return membershipService.getAll();
    }

    @GetMapping(value = "/membership/{membership_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Get membership type", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity getMembershipTypeById(@PathVariable("membership_id") int id) {
        return membershipService.getMembershipTypeById(id);
    }

    @PostMapping(value = "/updateMembership/{membership_id}/{type}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Membership update", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity updateMembership(@PathVariable("membership_id") int id, @PathVariable("type") String type) {
        return membershipService.updateMembership(id, type);
    }


}
