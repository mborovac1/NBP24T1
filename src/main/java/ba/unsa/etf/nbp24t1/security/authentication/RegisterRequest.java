package ba.unsa.etf.nbp24t1.security.authentication;

import ba.unsa.etf.nbp24t1.entity.auth.Role;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class RegisterRequest {

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private String username;

    private String phoneNumber;

    @JsonFormat(pattern = "dd.MM.yyyy.")
    private LocalDate birthDate;

    private String address;

    private String city;

    private Integer postcode;

    private String membership;

    private Role role;
}
