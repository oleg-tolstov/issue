package com.itissue.issue.repo;

import com.itissue.issue.models.Issue;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IssueRepo extends CrudRepository<Issue, Long> {
}
