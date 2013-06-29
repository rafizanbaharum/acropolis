package net.canang.acropolis.biz;

import net.canang.acropolis.core.dao.IssueDao;
import net.canang.acropolis.core.model.Issue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author rafizan.baharum
 * @since 6/29/13
 */
@Component("bizFinder")
public class BizFinderImpl implements BizFinder {

    @Autowired
    private IssueDao issueDao;

    @Override
    public List<Issue> findAround(Double radius, Double myLat, Double myLon) {
        return issueDao.findAround(radius, myLat, myLon);
    }

}
