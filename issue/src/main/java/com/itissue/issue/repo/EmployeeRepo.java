package com.itissue.issue.repo;

import com.itissue.issue.models.Employee;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepo extends CrudRepository<Employee, Long> {
}
