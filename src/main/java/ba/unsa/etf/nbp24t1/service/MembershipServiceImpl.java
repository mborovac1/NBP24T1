package ba.unsa.etf.nbp24t1.service;

import ba.unsa.etf.nbp24t1.entity.MembershipEntity;
import ba.unsa.etf.nbp24t1.entity.MembershipType;
import ba.unsa.etf.nbp24t1.exception.NotFoundException;
import ba.unsa.etf.nbp24t1.repository.MembershipRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class MembershipServiceImpl implements MembershipService {

    private final MembershipRepository membershipRepository;

    @Override
    public List<MembershipEntity> getAll() {
        return membershipRepository.findAll();
    }

    @Override
    public ResponseEntity getMembershipTypeById(int id){
        if(membershipRepository.existsById(id)) {
            return new ResponseEntity(membershipRepository.findById(id), HttpStatus.OK);
        } else {
            throw new NotFoundException(String.format("Membership with id %s doesn't exist.", id));
        }
    }

    @Override
    public ResponseEntity<?> updateMembership(int id, String type) {
        // Convert the string to the enum
        MembershipType membershipType;
        try {
            membershipType = MembershipType.valueOf(type.toUpperCase());
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>("Invalid membership type provided", HttpStatus.BAD_REQUEST);
        }

        // Check if the membership exists
        MembershipEntity foundMembership = membershipRepository.findById(id);
        if (foundMembership != null) {
            // Update the membership type
            foundMembership.setType(membershipType);

            // Save the updated membership
            membershipRepository.save(foundMembership);

            // Return the updated membership
            return new ResponseEntity<>(foundMembership, HttpStatus.OK);
        } else {
            // Membership not found
            return new ResponseEntity<>(String.format("Membership with id %s doesn't exist.", id), HttpStatus.NOT_FOUND);
        }
    }

}
