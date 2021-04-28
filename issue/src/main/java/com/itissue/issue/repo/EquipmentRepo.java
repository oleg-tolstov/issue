package com.itissue.issue.repo;

import com.itissue.issue.models.Equipment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EquipmentRepo extends CrudRepository<Equipment, Long> {

    @Query(
            value = "SELECT * FROM equipment e WHERE DATEDIFF(e.exp_date, now()) BETWEEN 1 AND EXTRACT(DAY FROM LAST_DAY(CURRENT_DATE))",
            nativeQuery = true
    )
    List<Equipment> comesToTheEnd();
}
