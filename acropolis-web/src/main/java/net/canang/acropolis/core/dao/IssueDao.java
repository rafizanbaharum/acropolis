package net.canang.acropolis.core.dao;

import net.canang.acropolis.core.model.Issue;
import net.canang.acropolis.core.model.IssueStatus;

import java.util.List;

/**
 * @author rafizan.baharum
 * @since 6/29/13
 */
public interface IssueDao {

    Issue findById(Long id);

    List<Issue> find();

    List<Issue> find(IssueStatus status);

    List<Issue> findAround(IssueStatus status, Double radius, Double latitude, Double longitude);

    void save(Issue issue);

    void update(Issue issue);

    void remove(Issue issue);
}
