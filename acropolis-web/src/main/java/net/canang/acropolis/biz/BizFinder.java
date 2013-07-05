package net.canang.acropolis.biz;

import net.canang.acropolis.core.model.Issue;

import java.util.List;

/**
 * @author rafizan.baharum
 * @since 6/29/13
 */
public interface BizFinder {

    List<Issue> findUnresolvedAround(Double radius, Double myLat, Double myLon);

    List<Issue> findResolvedAround(Double radius, Double myLat, Double myLon);

    void saveIssue(Issue issue);

}
