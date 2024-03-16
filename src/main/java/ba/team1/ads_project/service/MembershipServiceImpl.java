package ba.team1.ads_project.service;

import ba.team1.ads_project.repository.MembershipRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MembershipServiceImpl implements MembershipService {

    private final MembershipRepository membershipRepository;
}
