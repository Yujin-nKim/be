package onehajo.seurasaeng.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "shuttle", schema = "seurasaeng_test")
public class Shuttle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shuttle_id")
    private Long id;

    @NotNull
    @Column(name = "shuttle_name", columnDefinition = "VARCHAR(50)")
    private String shuttleName;

    @NotNull
    @Column(name = "departure", columnDefinition = "VARCHAR(50)")
    private String departure;

    @NotNull
    @Column(name = "destination", columnDefinition = "VARCHAR(50)")
    private String destination;

    @Column(name = "is_commute", columnDefinition = "BOOLEAN")
    private Boolean isCommute;

    @OneToMany(mappedBy = "shuttle", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Timetable> timetables = new ArrayList<>();
}