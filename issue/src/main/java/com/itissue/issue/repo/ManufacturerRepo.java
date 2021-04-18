package com.itissue.issue.repo;

import com.itissue.issue.models.Manufacturer;
import org.springframework.data.repository.CrudRepository;

public interface ManufacturerRepo extends CrudRepository<Manufacturer, Long> {
}
