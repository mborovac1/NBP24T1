package ba.unsa.etf.nbp24t1.service;

import ba.unsa.etf.nbp24t1.entity.CinemaUserEntity;
import ba.unsa.etf.nbp24t1.entity.MembershipEntity;
import ba.unsa.etf.nbp24t1.entity.MembershipType;
import ba.unsa.etf.nbp24t1.exception.NotFoundException;
import ba.unsa.etf.nbp24t1.repository.CinemaUserRepository;
import ba.unsa.etf.nbp24t1.repository.MembershipRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MembershipServiceImpl implements MembershipService {

    private final MembershipRepository membershipRepository;
    private final CinemaUserRepository cinemaUserRepository;

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
            //foundMembership.setDiscount(0.0);
            switch (membershipType) {
                case STANDARD:
                    foundMembership.setDiscount(0.0);
                    break;
                case STUDENT:
                    foundMembership.setDiscount(5.0);
                    break;
                case GOLD:
                    foundMembership.setDiscount(10.0);
                    break;
                case PLATINUM:
                    foundMembership.setDiscount(15.0);
                    break;
                case VIP:
                    foundMembership.setDiscount(20.0);
                    break;
                default:
                    foundMembership.setDiscount(1.0);
            }

            // Save the updated membership
            membershipRepository.save(foundMembership);

            // Return the updated membership
            return new ResponseEntity<>(foundMembership, HttpStatus.OK);
        } else {
            // Membership not found
            return new ResponseEntity<>(String.format("Membership with id %s doesn't exist.", id), HttpStatus.NOT_FOUND);
        }
    }


    public double getDiscountByUserId(Long userId) {
        Optional<CinemaUserEntity> cinemaUserOpt = cinemaUserRepository.findById(userId);
        if (cinemaUserOpt.isPresent()) {
            System.out.println(cinemaUserOpt.get().getUserId());
            CinemaUserEntity cinemaUser = cinemaUserOpt.get();
            Optional<MembershipEntity> membershipOpt = membershipRepository.findById(cinemaUser.getMembershipId());
            if (membershipOpt.isPresent()) {
                return membershipOpt.get().getDiscount();
            }
        }
        return 0.0;
    }

}
