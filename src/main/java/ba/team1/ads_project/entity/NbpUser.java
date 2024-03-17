package ba.team1.ads_project.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "nbp_user")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class NbpUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank(message = "First name must exist.")
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @NotBlank(message = "Last name must exist.")
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @NotBlank(message = "Email must exist.")
    @Email(message = "Email is not valid", regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @NotBlank(message = "Password must exist.")
    @Column(name = "password", nullable = false)
    private String password;

    @NotBlank(message = "Username must exist.")
    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "phone_number", length = 20)
    private String phoneNumber;

    @JsonFormat(pattern = "dd.MM.yyyy.")
    @Past(message = "Birth date must be in the past.")
    @Column(name = "birth_date")
    private LocalDate birthDate;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "role_id")
    private NbpRole nbpRole;

    @OneToOne(mappedBy = "nbpUser")
    private CinemaUser cinemaUser;
}
