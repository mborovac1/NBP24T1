package ba.unsa.etf.nbp24t1.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class NbpRole {

    @NotBlank(message = "Role name cannot be empty.")
    @Size(max = 50, message = "Role name must be less than 50 characters.")
    private String name;
}
