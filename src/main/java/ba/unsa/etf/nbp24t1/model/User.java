package ba.unsa.etf.nbp24t1.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class User {

    @NotBlank(message = "First name must exist.")
    private String firstName;

    @NotBlank(message = "Last name must exist.")
    private String lastName;

    @NotBlank(message = "Email must exist.")
    @Email(message = "Email is not valid", regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
    private String email;

    @NotBlank(message = "Password must exist.")
    private String password;

    @NotBlank(message = "Username must exist.")
    private String username;

    private String phoneNumber;

    @JsonFormat(pattern = "dd.MM.yyyy.")
    @Past(message = "Birth date must be in the past.")
    private LocalDate birthDate;

    private String address;

    private String city;

    private Integer postcode;

    private String membership;
}
