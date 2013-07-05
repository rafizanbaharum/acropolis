package net.canang.acropolis.core.dao;

import net.canang.acropolis.core.model.Issue;
import net.canang.acropolis.core.model.IssueImpl;
import net.canang.acropolis.core.model.IssueStatus;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;

import static net.canang.acropolis.core.model.IssueStatus.RESOLVED;
import static net.canang.acropolis.core.model.IssueStatus.UNRESOLVED;


/**
 * @author rafizan.baharum
 * @since 6/29/13
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AcropolisConfiguration.class})
public class IssueDaoTest {

    private Logger log = LoggerFactory.getLogger(IssueDaoTest.class);

    @Autowired
    private IssueDao issueDao;

    @Autowired
    private SessionFactory sessionFactory;
    private double radius;
    private double startLat;
    private double startLon;

    @Test
    public void createIssue() {
        Issue issue = new IssueImpl();
        issue.setLatitude(1.5333D);
        issue.setLongitude(103.6667D);
        issue.setStatus(IssueStatus.UNRESOLVED);
        issue.setReporter("Rafizan Uda Baharum");
        issue.setReportedDate(new Date());
        issue.setTitle("Kerosakan Lampu Jalan");
        issue.setDescription("Kerosakan lampu memnyebabkan ramai orang terkandas");
        issueDao.save(issue);

        issue = new IssueImpl();
        issue.setLatitude(1.5343D);
        issue.setLongitude(103.888D);
        issue.setStatus(IssueStatus.UNRESOLVED);
        issue.setReporter("Rafizan Uda Baharum");
        issue.setReportedDate(new Date());
        issue.setTitle("Kerosakan Lampu Jalan II");
        issue.setDescription("Kerosakan lampu memnyebabkan ramai orang terkandas II");
        issueDao.save(issue);

        issue = new IssueImpl();
        issue.setLatitude(1.5243D);
        issue.setLongitude(103.388D);
        issue.setStatus(IssueStatus.UNRESOLVED);
        issue.setReporter("Rafizan Uda Baharum");
        issue.setReportedDate(new Date());
        issue.setTitle("Kerosakan Lampu Jalan II");
        issue.setDescription("Kerosakan lampu memnyebabkan ramai orang terkandas II");
        issueDao.save(issue);

        issue = new IssueImpl();
        issue.setLatitude(2.5243D);
        issue.setLongitude(102.388D);
        issue.setStatus(IssueStatus.UNRESOLVED);
        issue.setReporter("Rafizan Uda Baharum");
        issue.setReportedDate(new Date());
        issue.setTitle("Kerosakan Lampu Jalan II");
        issue.setDescription("Kerosakan lampu memnyebabkan ramai orang terkandas II");
        issueDao.save(issue);

        radius = 10000.0D;
        startLat = 1.5333D;
        startLon = 103.388D;

        List<Issue> issues = issueDao.findAround(RESOLVED, radius, startLat, startLon);
        for (Issue i : issues) {
            log.debug("issue: " + i);
        }
    }


    @Test
    public void find() {
        List<Issue> issues = issueDao.find();
        for (Issue issue : issues) {
            log.debug("issue: " + issue);
        }
    }

    @Test
    public void findAround() {
        List<Issue> issues = issueDao.findAround(UNRESOLVED, 100.0D, 1.5333D, 103.388D);
        for (Issue issue : issues) {
            log.debug("issue: " + issue.getStatus());
            log.debug("issue: " + issue);
        }
    }

    @Test
    public void saveAndFind() {
        Issue issue = new IssueImpl();
        issue.setLatitude(1.5555D);
        issue.setLongitude(103.3333D);
        issue.setStatus(IssueStatus.UNRESOLVED);
        issue.setReporter("Rafizan Uda Baharum");
        issue.setReportedDate(new Date());
        issue.setTitle("Kerosakan Jalan");
        issue.setDescription("Kerosakan jalan");
        issueDao.save(issue);


        List<Issue> issues = issueDao.findAround(RESOLVED, 1D, 1.5555D, 103.3333D);
        for (Issue i : issues) {
            log.debug("issue: " + i);
        }
    }

    @Test
    public void findAndUpdate() {
        List<Issue> issues = issueDao.find();
        for (Issue issue : issues) {
            log.debug("status: " + issue.getStatus());
            issueDao.update(issue);
        }
    }
}
