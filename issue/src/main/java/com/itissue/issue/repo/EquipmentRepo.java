package com.itissue.issue.repo;

import com.itissue.issue.models.Equipment;
import org.springframework.data.repository.CrudRepository;

public interface EquipmentRepo extends CrudRepository<Equipment, Long> {
}
