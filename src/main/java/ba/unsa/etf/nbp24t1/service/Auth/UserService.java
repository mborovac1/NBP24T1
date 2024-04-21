package ba.unsa.etf.nbp24t1.service.Auth;

import ba.unsa.etf.nbp24t1.entity.Auth.Role;
import ba.unsa.etf.nbp24t1.entity.Auth.User;
import ba.unsa.etf.nbp24t1.repository.Auth.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public String getUsers() {
        List<User> users = userRepository.findAll();
        String json = null;
        try {
            StringBuilder sb = new StringBuilder("[");
            for (User user : users) {
                sb.append(user.toString()).append(",");
            }
            if (users.size() > 0) {
                sb.deleteCharAt(sb.length() - 1);
            }
            sb.append("]");
            json = sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error serializing elections to JSON: " + e.getMessage());
        }
        System.out.println("Serialized JSON: " + json);
        return json;
    }

    @PostConstruct
    private void createAdmin() {
        if(userRepository.findByEmail("cineadmin@gmail.com").isEmpty()) {
            var user = User.builder()
                    .ime("CineAdmin")
                    .prezime("CineAdminLast")
                    .datumRodjenja(LocalDate.of(2000, Month.FEBRUARY, 24))
                    .email("cineadmin@gmail.com")
                    .password(passwordEncoder.encode("12345"))
                    .brojTelefona("062646331")
                    .spol("M")
                    .role(Role.ADMIN)
                    .build();

            var savedUser = userRepository.save(user);
        }
    }
}
