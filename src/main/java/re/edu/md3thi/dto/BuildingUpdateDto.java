package re.edu.md3thi.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;
import re.edu.md3thi.entity.BuildingStatus;

import java.time.LocalDate;

@Getter
@Setter
public class BuildingUpdateDto {
    @NotBlank(message = "Tên tòa nhà không được để trống")
    @Size(max = 255, message = "Tên tòa nhà không được quá 255 kí tự")
    private String buildingName;

    @NotNull(message = "Diện tích không được để trống")
    @Positive(message = "Diện tích phải > 0")
    private Double buildingArea;  // Diện tích xd

    @NotBlank(message = "Đơn vị diện tích không được để trống")
    private String areaUnit;  // đơn vị dtich xd

    @NotNull(message = "Ngày khởi công không được để trống")
    @PastOrPresent(message = "Ngày khởi công không được ở tương lai")
    private LocalDate startDate;  // ngày khởi công

    @NotNull(message = "Thời gian xây dựng không được để trống")
    @Positive(message = "Thời gian phải > 0")
    private Integer time;  // tgian xd

    @NotBlank(message = "Đơn vị thời gian không được để trống")
    private String timeUnit;  // đvị tính tgian

    @NotBlank(message = "Thiết kế không được để trống")
    private String design;

    @NotBlank(message = "Nội dung không được để trống")
    private String content;

    @NotNull(message = "Trạng thái không được để trống")
    private BuildingStatus status;

    private MultipartFile image;
}
