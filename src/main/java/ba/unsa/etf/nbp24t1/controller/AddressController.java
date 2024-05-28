package ba.unsa.etf.nbp24t1.controller;

import ba.unsa.etf.nbp24t1.entity.AddressEntity;
import ba.unsa.etf.nbp24t1.service.AddressService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/addresses")
@CrossOrigin
@RestController
public class AddressController {

    private final AddressService addressService;

    @GetMapping("/")
    public List<AddressEntity> getAll() {
        return addressService.getAll();
    }

    @GetMapping(value = "/address/{address_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Users adress", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity getAddressById(@PathVariable("address_id") int id) {
        return addressService.getAddressById(id);
    }
}
