package re.edu.md3thi.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import re.edu.md3thi.dto.*;
import re.edu.md3thi.entity.Building;
import re.edu.md3thi.entity.BuildingStatus;
import re.edu.md3thi.service.BuildingService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/buildings")
public class BuildingController {
    private final BuildingService buildingService;

    // 1. Lấy tất cả tòa nhà
    @GetMapping
    public ResponseEntity<ApiResponse<PageResponseDto<BuildingResponseDto>>> getBuildings(
            @RequestParam(defaultValue = "0") int page) {
        PageResponseDto<BuildingResponseDto> data = buildingService.getAllBuildings(page);

        ApiResponse<PageResponseDto<BuildingResponseDto>> response = new ApiResponse<>(
                true, "Lấy danh sách thành công", data, null,
                HttpStatus.OK.value());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<BuildingResponseDto>> createBuilding(
            @Valid @RequestBody BuildingCreateDto dto) {
        BuildingResponseDto data = buildingService.create(dto);
        ApiResponse<BuildingResponseDto> response = new ApiResponse<>(
                true, "Thêm tòa nhà thành công", data, null,
                HttpStatus.CREATED.value());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<BuildingResponseDto>> update(
            @PathVariable Long id, @Valid @ModelAttribute BuildingUpdateDto dto) {
        BuildingResponseDto data = buildingService.update(id, dto);
        ApiResponse<BuildingResponseDto> response = new ApiResponse<>(
                true, "Cập nhật tòa nhà thành công", data, null,
                HttpStatus.OK.value());
        return ResponseEntity.ok(response);
    }

    // Tìm tòa nhà theo tên, trạng thái
    @GetMapping("/search")
    public ResponseEntity<ApiResponse<PageResponseDto<BuildingResponseDto>>> search(
            @RequestParam(required = false) String buildingName,
            @RequestParam(required = false)BuildingStatus status,
            @RequestParam(defaultValue = "0") int page
            ) {
        PageResponseDto<BuildingResponseDto> data = buildingService.searchBuilding(
                buildingName, status, page);
        ApiResponse<PageResponseDto<BuildingResponseDto>> response = new ApiResponse<>(
                true, "Tìm kiếm tòa nhà thành công", data, null,
                HttpStatus.OK.value());
        return ResponseEntity.ok(response);
    }

    // Thay đổi trạng thái xây dựng tòa nhà
    @PatchMapping("/{id}/status")
    public ResponseEntity<ApiResponse<BuildingResponseDto>> changeStatus(
            @PathVariable Long id, @RequestParam BuildingStatus status
    ) {
        BuildingResponseDto data = buildingService.changeStatus(id, status);

        ApiResponse<BuildingResponseDto> response = new ApiResponse<>(
                true, "Thay đổi trạng thái tòa nhà thành công", data, null,
                HttpStatus.OK.value());
        return ResponseEntity.ok(response);
    }
}
