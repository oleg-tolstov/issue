package com.itissue.issue.repo;

import com.itissue.issue.models.Issue;
import org.springframework.data.repository.CrudRepository;

public interface IssueRepo extends CrudRepository<Issue, Long> {
}
