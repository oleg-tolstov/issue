package com.itissue.issue.repo;

import com.itissue.issue.models.EquipmetType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EquipmentTypeRepo extends CrudRepository<EquipmetType, Long> {
}
