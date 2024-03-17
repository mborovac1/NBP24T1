package ba.team1.ads_project.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "nbp_role")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class NbpRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", length = 50, nullable = false, unique = true)
    private String name;

    @OneToMany(mappedBy = "nbpRole")
    private List<NbpUser> nbpUsers = new ArrayList<>();
}
