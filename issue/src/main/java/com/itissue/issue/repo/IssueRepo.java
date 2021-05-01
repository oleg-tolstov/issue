package com.itissue.issue.repo;

import com.itissue.issue.models.Issue;
import org.exolab.castor.types.DateTime;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IssueRepo extends CrudRepository<Issue, Long> {
	boolean existsIssueByEquipmentId(Long equipmentId);

	@Query(value = "select em.id as id_emp, em.name as name_emp, e.id as id_equ, e.name as name_equ, e.serial_number, i.date  from issue i, equipment e, employee em where i.equ_id=e.id and i.id_emp=em.id and i.id_emp = :employeeId",
			nativeQuery = true)
	List<Issue> findIssueByEmployeeId(@Param("employeeId") Long employeeId);

	List<Issue> findAllByEmployeeId(Long employeeId);
}
