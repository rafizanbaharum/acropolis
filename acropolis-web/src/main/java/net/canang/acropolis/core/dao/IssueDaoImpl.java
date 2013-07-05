package net.canang.acropolis.core.dao;

import net.canang.acropolis.core.model.Issue;
import net.canang.acropolis.core.model.IssueImpl;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.search.FullTextQuery;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.hibernate.search.query.dsl.Unit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author rafizan.baharum
 * @since 6/29/13
 */
@Transactional
@Repository("issueDao")
public class IssueDaoImpl implements IssueDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Issue findById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        return (Issue) session.get(Issue.class, id);
    }

    @Override
    public List<Issue> find() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select i from Issue i");
        return (List<Issue>) query.list();
    }

    @Override
    public List<Issue> findAround(Double radius, Double latitude, Double longitude) {
        FullTextSession fullTextSession = Search.getFullTextSession(sessionFactory.getCurrentSession());
        QueryBuilder builder = fullTextSession
                .getSearchFactory()
                .buildQueryBuilder().forEntity(IssueImpl.class).get();

        org.apache.lucene.search.Query luceneQuery = builder.spatial()
                .onCoordinates(Issue.FIELD)
                .within(radius, Unit.KM)
                .ofLatitude(latitude)
                .andLongitude(longitude)
                .createQuery();

        FullTextQuery fullTextQuery = fullTextSession.createFullTextQuery(luceneQuery, IssueImpl.class);
        return fullTextQuery.list();
    }

    @Override
    public void save(Issue issue) {
        Session session = sessionFactory.getCurrentSession();
        session.save(issue);
    }

    @Override
    public void update(Issue issue) {
        Session session = sessionFactory.getCurrentSession();
        session.update(issue);
    }

    @Override
    public void remove(Issue issue) {
        // TODO:

    }
}
