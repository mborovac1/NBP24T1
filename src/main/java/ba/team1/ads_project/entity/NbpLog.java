package ba.team1.ads_project.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "nbp_log")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class NbpLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "action_name", nullable = false)
    private String actionName;

    @Column(name = "table_name", nullable = false)
    private String tableName;

    @Builder.Default
    @Column(name = "created_at")
    private Timestamp createdAt = Timestamp.valueOf(LocalDateTime.now());

    @Column(name = "db_user", nullable = false)
    private String dbUser;
}
