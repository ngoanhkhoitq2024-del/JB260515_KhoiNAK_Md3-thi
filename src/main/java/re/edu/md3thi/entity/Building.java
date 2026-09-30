package re.edu.md3thi.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "buildings")
public class Building {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 100)
    private String buildingName;

    @Column(nullable = false)
    private Double buildingArea;  // Diện tích xd

    @Column(nullable = false, length = 10)
    private String areaUnit;  // đơn vị dtich xd

    @Column(nullable = false)
    private LocalDate startDate;  // ngày khởi công

    @Column(nullable = false)
    private Integer time;  // tgian xd

    @Column(nullable = false, length = 10)
    private String timeUnit;  // đvị tính tgian

    @Column(nullable = false, length = 255)
    private String design;

    @Column(nullable = false, length = 255)
    private String content;

    @Column(nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private BuildingStatus status;

    private String image;
}
