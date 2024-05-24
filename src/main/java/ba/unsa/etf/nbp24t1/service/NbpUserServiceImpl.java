package ba.unsa.etf.nbp24t1.service;

import ba.unsa.etf.nbp24t1.entity.AddressEntity;
import ba.unsa.etf.nbp24t1.entity.CinemaUserEntity;
import ba.unsa.etf.nbp24t1.entity.CityEntity;
import ba.unsa.etf.nbp24t1.entity.NbpUserEntity;
import ba.unsa.etf.nbp24t1.exception.AlreadyExistsException;
import ba.unsa.etf.nbp24t1.exception.NotFoundException;
import ba.unsa.etf.nbp24t1.model.User;
import ba.unsa.etf.nbp24t1.repository.*;
import ba.unsa.etf.nbp24t1.repository.Auth.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class NbpUserServiceImpl implements NbpUserService {

    private final NbpUserRepository nbpUserRepository;
    private final NbpRoleRepository nbpRoleRepository;
    private final AddressRepository addressRepository;
    private final CityRepository cityRepository;
    private final CinemaUserRepository cinemaUserRepository;
    private final UserRepository userRepository;
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
    public void add(User user) {
        if (nbpUserRepository.existsByEmail(user.getEmail()))
            throw new AlreadyExistsException(String.format("User with email %s already exists.", user.getEmail()));
        var nbpUserEntity = mapToNbpUserEntity(user);
        createCinemaUser(user, nbpUserRepository.save(nbpUserEntity));
    }

    @Override
    public void update(Long id, User user) {
        // TODO: Implement
    }

@Transactional
@Override
public ResponseEntity delete(Long userId) {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    try {
        // Attempt to find CinemaUserEntity
        logger.info("Attempting to find CinemaUserEntity with userId: {}", userId);
        CinemaUserEntity cinemaUser = cinemaUserRepository.findByUserId(userId).orElseThrow(() -> {
            logger.error("Id not found: {}", userId);
            return new IllegalArgumentException("Id not found");
        });
        logger.info("Found CinemaUserEntity: {}", cinemaUser);

        // Delete CinemaUserEntity
        cinemaUserRepository.delete(cinemaUser);
        logger.info("Successfully deleted CinemaUserEntity with userId: {}", userId);

        // Attempt to find NbpUserEntity
        logger.info("Attempting to find NbpUserEntity with id: {}", userId);
        NbpUserEntity nbpUserEntity = nbpUserRepository.findById(userId).orElseThrow(() -> {
            logger.error("Id not found: {}", userId);
            return new IllegalArgumentException("Id not found");
        });
        logger.info("Found NbpUserEntity: {}", nbpUserEntity);

        // Attempt to find User in userRepository using email
        logger.info("Attempting to find User in userRepository with email: {}", nbpUserEntity.getEmail());
        ba.unsa.etf.nbp24t1.entity.Auth.User user = userRepository.findByEmail(nbpUserEntity.getEmail()).orElseThrow(() -> {
            logger.error("User with email not found: {}", nbpUserEntity.getEmail());
            return new IllegalArgumentException("User with email not found");
        });
        logger.info("Found User: {}", user);

        // Delete User from userRepository
        userRepository.delete(user);
        logger.info("Successfully deleted User with email: {}", user.getEmail());

        // Delete NbpUserEntity
        nbpUserRepository.delete(nbpUserEntity);
        logger.info("Successfully deleted NbpUserEntity with id: {}", userId);

        return ResponseEntity.ok("Deleted");
    } catch (Exception e) {
        logger.error("Error occurred while deleting user with userId: {}", userId, e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
    }
}
    private NbpUserEntity mapToNbpUserEntity(User user) {
        // TODO: Create CinemaUser and map it to this NbpUser
        var nbpUserEntity = new NbpUserEntity();

        String firstName = user.getFirstName();
        if (StringUtils.isNotBlank(firstName))
            nbpUserEntity.setFirstName(firstName);

        String lastName = user.getLastName();
        if (StringUtils.isNotBlank(lastName))
            nbpUserEntity.setLastName(lastName);

        String email = user.getEmail();
        if (StringUtils.isNotBlank(email))
            nbpUserEntity.setEmail(email);

        String password = user.getPassword();
        if (StringUtils.isNotBlank(password))
            nbpUserEntity.setPassword(passwordEncoder.encode(password));

        String username = user.getUsername();
        if (StringUtils.isNotBlank(username))
            nbpUserEntity.setUsername(username);

        String phoneNumber = user.getPhoneNumber();
        if (StringUtils.isNotBlank(phoneNumber))
            nbpUserEntity.setPhoneNumber(phoneNumber);

        LocalDate birthDate = user.getBirthDate();
        if (birthDate != null && !birthDate.isAfter(LocalDate.now()))
            nbpUserEntity.setBirthDate(birthDate);

        String address = user.getAddress();
        if (StringUtils.isNotBlank(address)) {
            var addressEntity = addressRepository.findByName(address)
                    .orElseGet(() -> createAddressEntity(user));
            nbpUserEntity.setAddressId(addressEntity.getId());
        }

        String roleUser = "ROLE_USER";
        var roleEntity = nbpRoleRepository.findByName(roleUser)
                .orElseThrow(() -> new NotFoundException(String.format("Role with name %s does not exist.", roleUser)));
        nbpUserEntity.setRoleId(roleEntity.getId());

        return nbpUserEntity;
    }

    private void createCinemaUser(User user, NbpUserEntity nbpUserEntity) {
        String membershipType = user.getMembership();
        var cinemaUserEntity = new CinemaUserEntity();
        cinemaUserEntity.setUserId(nbpUserEntity.getId());
        if (StringUtils.isNotBlank(membershipType)) {
            var membershipEntity = membershipRepository.findByType(membershipType);
            membershipEntity.ifPresent(entity -> cinemaUserEntity.setMembershipId(entity.getId()));
        }
        cinemaUserRepository.save(cinemaUserEntity);
    }

    private AddressEntity createAddressEntity(User user) {
        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setName(user.getAddress());

        String city = user.getCity();
        if (StringUtils.isNotBlank(city)) {
            var cityEntity = cityRepository.findByName(city)
                    .orElseGet(() -> createCityEntity(user));
            addressEntity.setCityId(cityEntity.getId());
        }

        addressRepository.save(addressEntity);
        return addressEntity;
    }

    private CityEntity createCityEntity(User user) {
        CityEntity cityEntity = new CityEntity();
        cityEntity.setName(user.getCity());

        var postcode = user.getPostcode();
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
