package ba.unsa.etf.nbp24t1.service;

import ba.unsa.etf.nbp24t1.entity.MembershipEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface MembershipService {

    List<MembershipEntity> getAll();
    ResponseEntity getMembershipTypeById(int id);
    ResponseEntity updateMembership(int id, String type);
}
