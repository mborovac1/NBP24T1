package ba.unsa.etf.nbp24t1.entity;

import ba.unsa.etf.nbp24t1.entity.Auth.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;

@Entity
@Table(name = "NBP_USER", schema = "NBP")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class NbpUserEntity implements UserDetails {

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
    private Long addressId;

    @Column(name = "ROLE_ID")
    private Long roleId;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return fetchAuthoritiesByRoleId(roleId);
    }

    private Collection<? extends GrantedAuthority> fetchAuthoritiesByRoleId(Long roleId) {
        if (roleId != null) {
            Role role = getRoleByRoleId(roleId);
            if (role != null) {
                return role.getAuthorities();
            }
        }
        return Collections.emptyList();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    private Role getRoleByRoleId(Long roleId) {
        switch (roleId.intValue()) {
            case 70: // Role.USER
                return Role.USER;
            case 71: // Role.ADMIN
                return Role.ADMIN;
            default:
                return null; // Unknown roleId
        }
    }
}
