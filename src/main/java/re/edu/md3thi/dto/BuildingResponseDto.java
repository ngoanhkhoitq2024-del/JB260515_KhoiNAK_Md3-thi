package re.edu.md3thi.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import re.edu.md3thi.entity.Building;
import re.edu.md3thi.entity.BuildingStatus;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BuildingResponseDto {
    private Long id;
    private String buildingName;
    private Double buildingArea;  // Diện tích xd
    private String areaUnit;  // đơn vị dtich xd
    private LocalDate startDate;  // ngày khởi công
    private Integer time;  // tgian xd
    private String timeUnit;  // đvị tính tgian
    private String design;
    private String content;
    private BuildingStatus status;
    private String image;

    public BuildingResponseDto(Building building) {
        this.id = building.getId();
        this.buildingName = building.getBuildingName();
        this.buildingArea = building.getBuildingArea();
        this.areaUnit = building.getAreaUnit();
        this.startDate = building.getStartDate();
        this.time = building.getTime();
        this.timeUnit = building.getTimeUnit();
        this.design = building.getDesign();
        this.content = building.getContent();
        this.status = building.getStatus();
        this.image = building.getImage();
    }
}
