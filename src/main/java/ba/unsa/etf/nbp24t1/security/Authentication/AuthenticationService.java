package ba.unsa.etf.nbp24t1.security.Authentication;

import ba.unsa.etf.nbp24t1.entity.AddressEntity;
import ba.unsa.etf.nbp24t1.entity.Auth.*;
import ba.unsa.etf.nbp24t1.entity.CinemaUserEntity;
import ba.unsa.etf.nbp24t1.entity.CityEntity;
import ba.unsa.etf.nbp24t1.entity.NbpUserEntity;
import ba.unsa.etf.nbp24t1.exception.NotFoundException;
import ba.unsa.etf.nbp24t1.repository.AddressRepository;
import ba.unsa.etf.nbp24t1.repository.Auth.TokenRepository;
import ba.unsa.etf.nbp24t1.repository.Auth.UserRepository;
import ba.unsa.etf.nbp24t1.repository.CinemaUserRepository;
import ba.unsa.etf.nbp24t1.repository.CityRepository;
import ba.unsa.etf.nbp24t1.repository.NbpUserRepository;
import ba.unsa.etf.nbp24t1.security.JwtService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.SecureRandom;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final TokenRepository tokenRepository;
    private final CityRepository cityRepository;
    private final AddressRepository addressRepository;
    private final NbpUserRepository nbpUserRepository;

    private final CinemaUserRepository cinemaUserRepository;
//    @Autowired
//    private JavaMailSender javaMailSender;

    public static String generateRandomPassword(int length) {
        if (length < 3) {
            throw new IllegalArgumentException("Password length must be at least 3");
        }

        String uppercaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowercaseLetters = "abcdefghijklmnopqrstuvwxyz";
        String digits = "0123456789";
        String specialCharacters = "!@#$%^&*()-_=+";
        String allCharacters = uppercaseLetters + lowercaseLetters + digits + specialCharacters;

        SecureRandom secureRandom = new SecureRandom();
        StringBuilder password = new StringBuilder();

        // Ensure at least one capital letter
        password.append(uppercaseLetters.charAt(secureRandom.nextInt(uppercaseLetters.length())));

        // Ensure at least one digit
        password.append(digits.charAt(secureRandom.nextInt(digits.length())));

        // Fill the remaining characters
        for (int i = 2; i < length; i++) {
            int randomIndex = secureRandom.nextInt(allCharacters.length());
            password.append(allCharacters.charAt(randomIndex));
        }

        return password.toString();
    }

//    public ResponseEntity<String> sendPasswordViaEmail(String email) {
//        try {
//            var user = userRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("User not found"));
//
//            String randomPassword = generateRandomPassword(16);
//            // Send the email with user information
//            sendPasswordEmail(user.getEmail(), randomPassword);
//            user.setPassword(passwordEncoder.encode(randomPassword));
//            userRepository.save(user);
//
//            return ResponseEntity.ok("Password sent successfully");
//        } catch (NotFoundException e) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
//        }
//    }

//    private void sendPasswordEmail(String email, String randomPassword) {
//
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setTo(email);
//        message.setSubject("Cineflexx - zaboravljeni password");
//        message.setText("Vaša email adresa: " + email + "\nVaš privremeni password: " + randomPassword + "\n\n" + "Molimo Vas promijenite šifru nakon što se prijavite na stranicu!");
//
//        // Send the email
//        javaMailSender.send(message);
//    }

//    public ResponseEntity<String> resetPassword(String email, String oldPassword, String newPassword) {
//        try {
//            var user = userRepository.findByEmail(email).orElseThrow(() -> new NePostojiException("User not found"));
//
//            if (!passwordEncoder.matches(oldPassword, user.getPassword()))
//                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Wrong current password!");
//
//            user.setPassword(passwordEncoder.encode(newPassword));
//            userRepository.save(user);
//            return ResponseEntity.ok("Password reset successfully");
//        } catch (NePostojiException e) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
//        }
//    }

    public ResponseEntity<String> resetPassword(Reset request) {
        try {
            var user = userRepository.findByEmail(request.getEmail()).orElseThrow(() -> new NotFoundException("User not found"));

            if (!passwordEncoder.matches(request.getOldPassword(), user.getPassword())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Wrong current password!");
            }

            user.setPassword(passwordEncoder.encode(request.getNewPassword()));
            userRepository.save(user);
            return ResponseEntity.ok("Password reset successfully");
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
    }



    public AuthenticationResponse register(RegisterRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new NotFoundException("Email adresa je već u upotrebi");
        }

        var user = User.builder()
                .ime(request.getIme())
                .prezime(request.getPrezime())
                .datumRodjenja(request.getDatumRodjenja())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .brojTelefona(request.getBrojTelefona())
                .spol(request.getSpol())
                .role(request.getRole())
                .build();

        System.out.println(user + " sadadasdadasddasda");

        var savedUser = userRepository.save(user);

        var newCity = new CityEntity();
        Optional<CityEntity> existingCity = cityRepository.findByName(request.getCityName());
        //Optional<CityEntity> existingCity = cityRepository.findByName("Sarajevo");
        if (existingCity.isPresent()) {
            newCity = existingCity.get();
        }
        else {
//            newCity.setName("Sarajevo");
//            newCity.setPostcode(71000);
            newCity.setName(request.getCityName());
            newCity.setPostcode(request.getPostcode());
            cityRepository.save(newCity);
        }

        var newAddress = new AddressEntity();
        //Optional<AddressEntity> existingAddress = addressRepository.findByName("Đoke Mazalica 2");
        Optional<AddressEntity> existingAddress = addressRepository.findByName(request.getAddressName());
        if (existingAddress.isPresent()) {
            newAddress = existingAddress.get();
        }
        else {
            //newAddress.setName("Sarajevo");
            newAddress.setName(request.getAddressName());
            newAddress.setCityId(newCity.getId());
            addressRepository.save(newAddress);
        }

        var nbpUser = new NbpUserEntity();
        nbpUser.setFirstName(request.getIme());
        nbpUser.setLastName(request.getPrezime());
        nbpUser.setBirthDate(request.getDatumRodjenja());
        nbpUser.setPhoneNumber(request.getBrojTelefona());
        nbpUser.setUsername(request.getUsername());
        nbpUser.setEmail(request.getEmail());
        nbpUser.setPassword(passwordEncoder.encode(request.getPassword()));
        nbpUser.setRoleId(70L);
        nbpUser.setAddressId(newAddress.getId());

        var saveKorisnik = nbpUserRepository.save(nbpUser);

        var cinemaUser = new CinemaUserEntity();
        cinemaUser.setUserId(nbpUser.getId());
        cinemaUser.setMembershipId(null); //empty in beginning

        var saveCinemaUser = cinemaUserRepository.save(cinemaUser);

        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);

         saveUserToken(user, jwtToken);

        var token = tokenRepository.findAllValidTokenByUser(user.getID()).get(0).token;

        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .build();
    }

    public AuthenticationResponse addUser(RegisterRequest request) {

        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new NotFoundException("Email adresa je već u upotrebi");
        }


        var user = User.builder()
                .ime(request.getIme())
                .prezime(request.getPrezime())
                .datumRodjenja(request.getDatumRodjenja())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .brojTelefona(request.getBrojTelefona())
                .spol(request.getSpol())
                .role(request.getRole())
                .build();

//        var korisnik = new Korisnik();
//        korisnik.setIme(request.getIme());
//        korisnik.setPrezime(request.getPrezime());
//        korisnik.setSpol(request.getSpol());
//        korisnik.setDatumRodjenja(request.getDatumRodjenja());
//        korisnik.setBrojTelefona(request.getBrojTelefona());
//        korisnik.setEmail(request.getEmail());
//
//        var savedUser = userRepository.save(user);
//        var saveKorisnik = korisnikRepository.save(korisnik);

        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);

//        saveUserToken(savedUser, jwtToken);

        var restUser = new User();
        restUser.setIme(request.getIme());
        restUser.setPrezime(request.getPrezime());
        restUser.setDatumRodjenja(request.getDatumRodjenja());
        restUser.setBrojTelefona(request.getBrojTelefona());
        restUser.setEmail(request.getEmail());
        restUser.setRole(Role.USER);
        restUser.setSpol(request.getSpol());

        var token = tokenRepository.findAllValidTokenByUser(user.getID()).get(0).token;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", "Bearer " + token);
        HttpEntity<User> headerForRest = new HttpEntity<>(restUser, headers);
        //restTemplate.postForObject("http://preporucivanje-sadrzaja-pogodnosti/korisnici/dodaj", headerForRest, User.class);
        //restTemplate.postForObject("http://rezervacija-karata/dodajKorisnika", headerForRest, User.class);

        //GrpcClient.log(user.getId(),"AuthService","register","Success");




//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setTo(request.getEmail());
//        message.setSubject("Cineflexx - registracija");
//        message.setText("Vaši korisnički podaci: \n" + "email: " + request.getEmail() + "\npassword: " + request.getPassword() + "\n\n" + "Molimo Vas promijenite šifru nakon što se prijavite na stranicu!");
//
//            // Send the email
//        javaMailSender.send(message);


        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .build();
    }

    public AuthenticationResponse login(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(), request.getPassword()
                )
        );

        var user = userRepository.findByEmail(request.getEmail()).orElseThrow();

        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);

        revokeAllUserTokens(user);
        saveUserToken(user, jwtToken);

        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .build();
    }

    private void revokeAllUserTokens(User user) {
        var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getID());

        if (validUserTokens.isEmpty())
            return;

        validUserTokens.forEach(t -> {
            t.setExpired(true);
            t.setRevoked(true);
        });

        tokenRepository.saveAll(validUserTokens);
    }

    private void saveUserToken(User savedUser, String jwtToken) {
        var token = Token.builder()
                .user(savedUser)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }

    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String refreshToken;
        final String userEmail;

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return;
        }
        refreshToken = authHeader.substring(7);
        userEmail = jwtService.extractUsername(refreshToken);
        if (userEmail != null) {
            var user = this.userRepository.findByEmail(userEmail)
                    .orElseThrow();
            if (jwtService.isTokenValid(refreshToken, user)) {
                var accessToken = jwtService.generateToken(user);
                revokeAllUserTokens(user);
                saveUserToken(user, accessToken);
                var authResponse = AuthenticationResponse.builder()
                        .accessToken(accessToken)
                        .refreshToken(refreshToken)
                        .build();
                new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
            }
        }
    }


    public void logout(String email) {
        var user = userRepository.findByEmail(email);
        if(user != null){
            var allUserTokens = user.get().getTokens();
            for(var token : allUserTokens){
                token.setExpired(true);
                token.setRevoked(true);
                tokenRepository.delete(token);
            }
        }
    }
}
