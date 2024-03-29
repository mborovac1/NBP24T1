package ba.unsa.etf.nbp24t1.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "NBP_USER", schema = "NBP")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class NbpUserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @NotBlank(message = "First name must exist.")
    @Column(name = "FIRST_NAME", nullable = false)
    private String firstName;

    @NotBlank(message = "Last name must exist.")
    @Column(name = "LAST_NAME", nullable = false)
    private String lastName;

    @NotBlank(message = "Email must exist.")
    @Email(message = "Email is not valid", regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
    @Column(name = "EMAIL", nullable = false, unique = true)
    private String email;

    @NotBlank(message = "Password must exist.")
    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @NotBlank(message = "Username must exist.")
    @Column(name = "USERNAME", nullable = false, unique = true)
    private String username;

    @Column(name = "PHONE_NUMBER", length = 20)
    private String phoneNumber;

    @Past(message = "Birth date must be in the past.")
    @Column(name = "BIRTH_DATE")
    private LocalDate birthDate;

    @Column(name = "ADDRESS_ID")
    private Integer addressId;

    @Column(name = "ROLE_ID")
    private Integer roleId;
}
