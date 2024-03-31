package ba.unsa.etf.nbp24t1.service;

import ba.unsa.etf.nbp24t1.entity.MembershipEntity;
import ba.unsa.etf.nbp24t1.repository.MembershipRepository;
import lombok.RequiredArgsConstructor;
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
}
