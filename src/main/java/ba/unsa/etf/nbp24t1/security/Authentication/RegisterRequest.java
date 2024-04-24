package ba.unsa.etf.nbp24t1.security.Authentication;

import ba.unsa.etf.nbp24t1.entity.Auth.Role;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {
    private String ime;
    private String prezime;
    @JsonFormat(pattern = "dd.MM.yyyy.")
    private LocalDate datumRodjenja;
    private String brojTelefona;
    private String email;
    private String username;
    private String password;
    private Role role;
    private String spol;  //NEMA
    private Integer postcode;
    private String cityName;
    private String addressName;

}
