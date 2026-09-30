package re.edu.md3thi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import re.edu.md3thi.dto.BuildingCreateDto;
import re.edu.md3thi.dto.BuildingResponseDto;
import re.edu.md3thi.dto.BuildingUpdateDto;
import re.edu.md3thi.dto.PageResponseDto;
import re.edu.md3thi.entity.Building;
import re.edu.md3thi.entity.BuildingStatus;
import re.edu.md3thi.exception.DuplicateResourceException;
import re.edu.md3thi.exception.InvalidStatusException;
import re.edu.md3thi.exception.ResourceNotFoundException;
import re.edu.md3thi.repository.IBuildingRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BuildingService {
    private final IBuildingRepository iBuildingRepository;

    public PageResponseDto<BuildingResponseDto> getAllBuildings(int page) {
        Pageable pageable = PageRequest.of(page, 5);
        Page<Building> buildings = iBuildingRepository.findAll(pageable);
        List<BuildingResponseDto> data = buildings.getContent().stream().map(BuildingResponseDto::new).toList();
        return new PageResponseDto<>(buildings.map(BuildingResponseDto::new).getContent(),
                buildings.getNumber(), buildings.getSize(),
                buildings.getTotalElements(), buildings.getTotalPages());
    }

    // 2. Thêm mới
    public BuildingResponseDto create(BuildingCreateDto dto) {
        if (iBuildingRepository.existsByBuildingName(dto.getBuildingName())) {
            throw new DuplicateResourceException("Tên tòa nhà đã tồn tại");
        }
        Building building = new Building();
        building.setBuildingName(dto.getBuildingName());
        building.setBuildingArea(dto.getBuildingArea());
        building.setAreaUnit(dto.getAreaUnit());
        building.setStartDate(dto.getStartDate());
        building.setTime(dto.getTime());
        building.setTimeUnit(dto.getTimeUnit());
        building.setDesign(dto.getDesign());
        building.setContent(dto.getContent());
        building.setStatus(dto.getStatus());

        Building saveBuilding = iBuildingRepository.save(building);
        return new BuildingResponseDto(saveBuilding);

    }

    // 3. update
    public BuildingResponseDto update(Long id, BuildingUpdateDto dto) {

        Building building = iBuildingRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Không tìm thấy tòa nhà có id: " + id));
        if (iBuildingRepository.existsByBuildingNameAndIdNot(dto.getBuildingName(), id)) {
            throw new DuplicateResourceException("Tên tòa nhà đã tồn tại");
        }
        building.setBuildingName(dto.getBuildingName());
        building.setBuildingArea(dto.getBuildingArea());
        building.setAreaUnit(dto.getAreaUnit());
        building.setStartDate(dto.getStartDate());
        building.setTime(dto.getTime());
        building.setTimeUnit(dto.getTimeUnit());
        building.setDesign(dto.getDesign());
        building.setContent(dto.getContent());
        building.setStatus(dto.getStatus());

        if (dto.getImage() != null && !dto.getImage().isEmpty()) {
            String fileName = dto.getImage().getOriginalFilename();
            building.setImage(fileName);
        }

        Building saveBuilding = iBuildingRepository.save(building);

        return new BuildingResponseDto(saveBuilding);
    }

    // 4. Tìm theo tên, trạng thái
    public PageResponseDto<BuildingResponseDto> searchBuilding(
            String buildingName, BuildingStatus status, int page) {
        Pageable pageable = PageRequest.of(page, 5);
        Page<Building> buildings = iBuildingRepository.search(buildingName, status, pageable);

        List<BuildingResponseDto> data = buildings.map(BuildingResponseDto::new).getContent();

        return new PageResponseDto<>(data, buildings.getNumber(), buildings.getSize(),
                buildings.getTotalElements(), buildings.getTotalPages());
    }

    // 5. Thay đổi trạng thái xây dựng tòa nhà
    public BuildingResponseDto changeStatus(Long id, BuildingStatus newStatus) {
        Building building = iBuildingRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Không tìm thấy tòa nhà có id: " + id));
        if (building.getStatus() == BuildingStatus.ĐÃ_HOÀN_THÀNH) {
            throw new InvalidStatusException("Tòa nhà đã hoàn thành, không thể thay đổi trạng thái");
        }
        building.setStatus(newStatus);

        Building saveBuilding = iBuildingRepository.save(building);

        return new BuildingResponseDto(saveBuilding);
    }
}
