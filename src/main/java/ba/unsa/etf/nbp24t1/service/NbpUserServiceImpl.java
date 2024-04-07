package ba.unsa.etf.nbp24t1.service;

import ba.unsa.etf.nbp24t1.entity.AddressEntity;
import ba.unsa.etf.nbp24t1.entity.CinemaUserEntity;
import ba.unsa.etf.nbp24t1.entity.CityEntity;
import ba.unsa.etf.nbp24t1.entity.NbpUserEntity;
import ba.unsa.etf.nbp24t1.exception.AlreadyExistsException;
import ba.unsa.etf.nbp24t1.exception.NotFoundException;
import ba.unsa.etf.nbp24t1.model.NbpUser;
import ba.unsa.etf.nbp24t1.repository.*;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@Service
public class NbpUserServiceImpl implements NbpUserService {

    private final NbpUserRepository nbpUserRepository;
    private final NbpRoleRepository nbpRoleRepository;
    private final AddressRepository addressRepository;
    private final CityRepository cityRepository;
    private final CinemaUserRepository cinemaUserRepository;
    private final MembershipRepository membershipRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<NbpUserEntity> getAll() {
        return nbpUserRepository.findAll();
    }

    @Override
    public NbpUserEntity getById(Long id) {
        return findUserById(id);
    }

    @Override
    public void add(NbpUser nbpUser) {
        if (nbpUserRepository.existsByEmail(nbpUser.getEmail()))
            throw new AlreadyExistsException(String.format("User with email %s already exists.", nbpUser.getEmail()));
        var nbpUserEntity = mapToNbpUserEntity(nbpUser);
        createCinemaUser(nbpUser, nbpUserRepository.save(nbpUserEntity));
    }

    @Override
    public void update(Long id, NbpUser nbpUser) {
        // TODO: Implement
    }

    @Override
    public void delete(Long id) {
        // TODO: Implement
    }

    private NbpUserEntity mapToNbpUserEntity(NbpUser nbpUser) {
        // TODO: Create CinemaUser and map it to this NbpUser
        var nbpUserEntity = new NbpUserEntity();

        String firstName = nbpUser.getFirstName();
        if (StringUtils.isNotBlank(firstName))
            nbpUserEntity.setFirstName(firstName);

        String lastName = nbpUser.getLastName();
        if (StringUtils.isNotBlank(lastName))
            nbpUserEntity.setLastName(lastName);

        String email = nbpUser.getEmail();
        if (StringUtils.isNotBlank(email))
            nbpUserEntity.setEmail(email);

        String password = nbpUser.getPassword();
        if (StringUtils.isNotBlank(password))
            nbpUserEntity.setPassword(passwordEncoder.encode(password));

        String username = nbpUser.getUsername();
        if (StringUtils.isNotBlank(username))
            nbpUserEntity.setUsername(username);

        String phoneNumber = nbpUser.getPhoneNumber();
        if (StringUtils.isNotBlank(phoneNumber))
            nbpUserEntity.setPhoneNumber(phoneNumber);

        LocalDate birthDate = nbpUser.getBirthDate();
        if (birthDate != null && !birthDate.isAfter(LocalDate.now()))
            nbpUserEntity.setBirthDate(birthDate);

        String address = nbpUser.getAddress();
        if (StringUtils.isNotBlank(address)) {
            var addressEntity = addressRepository.findByName(address)
                    .orElseGet(() -> createAddressEntity(nbpUser));
            nbpUserEntity.setAddressId(addressEntity.getId());
        }

        String roleUser = "ROLE_USER";
        var roleEntity = nbpRoleRepository.findByName(roleUser)
                .orElseThrow(() -> new NotFoundException(String.format("Role with name %s does not exist.", roleUser)));
        nbpUserEntity.setRoleId(roleEntity.getId());

        return nbpUserEntity;
    }

    private void createCinemaUser(NbpUser nbpUser, NbpUserEntity nbpUserEntity) {
        String membershipType = nbpUser.getMembership();
        var cinemaUserEntity = new CinemaUserEntity();
        cinemaUserEntity.setUserId(nbpUserEntity.getId());
        if (StringUtils.isNotBlank(membershipType)) {
            var membershipEntity = membershipRepository.findByType(membershipType);
            membershipEntity.ifPresent(entity -> cinemaUserEntity.setMembershipId(entity.getId()));
        }
        cinemaUserRepository.save(cinemaUserEntity);
    }

    private AddressEntity createAddressEntity(NbpUser nbpUser) {
        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setName(nbpUser.getAddress());

        String city = nbpUser.getCity();
        if (StringUtils.isNotBlank(city)) {
            var cityEntity = cityRepository.findByName(city)
                    .orElseGet(() -> createCityEntity(nbpUser));
            addressEntity.setCityId(cityEntity.getId());
        }

        addressRepository.save(addressEntity);
        return addressEntity;
    }

    private CityEntity createCityEntity(NbpUser nbpUser) {
        CityEntity cityEntity = new CityEntity();
        cityEntity.setName(nbpUser.getCity());

        var postcode = nbpUser.getPostcode();
        if (postcode != null)
            cityEntity.setPostcode(postcode);

        cityRepository.save(cityEntity);
        return cityEntity;
    }

    private NbpUserEntity findUserById(Long id) {
        return nbpUserRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("User with id %d does not exist.", id)));
    }
}
