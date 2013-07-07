package net.canang.acropolis.biz;

import net.canang.acropolis.core.dao.IssueDao;
import net.canang.acropolis.core.model.Issue;
import net.canang.acropolis.core.model.IssueStatus;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

/**
 * @author rafizan.baharum
 * @since 6/29/13
 */
@Component("bizFinder")
public class BizFinderImpl implements BizFinder {

    public static final String KEY_PREFIX = "ACR";
    public static final String KEY_FORMAT = "00000";

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private IssueDao issueDao;

    @Override
    public List<Issue> findUnresolvedAround(Double radius, Double myLat, Double myLon) {
        return issueDao.findAround(IssueStatus.UNRESOLVED, radius, myLat, myLon);
    }

    @Override
    public List<Issue> findResolvedAround(Double radius, Double myLat, Double myLon) {
        return issueDao.findAround(IssueStatus.RESOLVED, radius, myLat, myLon);
    }

    @Override
    public void saveIssue(Issue issue) {
        NumberFormat formatter = new DecimalFormat(KEY_FORMAT);
        issue.setKey(KEY_PREFIX + "-" + formatter.format(issueDao.count()));
        issueDao.save(issue);
        sessionFactory.getCurrentSession().flush();
    }
}
