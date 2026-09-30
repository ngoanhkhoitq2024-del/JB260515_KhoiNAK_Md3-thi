package re.edu.md3thi.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import re.edu.md3thi.entity.Building;
import re.edu.md3thi.entity.BuildingStatus;

@Repository
public interface IBuildingRepository extends JpaRepository<Building, Long> {
    boolean existsByBuildingName(String buildingName);

    boolean existsByBuildingNameAndIdNot(String buildingName, Long id);

    @Query("""
    SELECT b FROM Building b
    WHERE (:buildingName IS NULL OR 
            LOWER(b.buildingName) LIKE LOWER(concat('%', :buildingName, '%')))
        AND (:status IS NULL OR b.status = :status)
""")
    Page<Building> search(@Param("buildingName") String buildingName,
                          @Param("status") BuildingStatus status,
                          Pageable pageable);

}
