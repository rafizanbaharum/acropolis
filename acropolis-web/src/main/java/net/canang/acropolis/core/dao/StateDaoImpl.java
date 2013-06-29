package net.canang.acropolis.core.dao;

import net.canang.acropolis.core.model.State;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author rafizan.baharum
 * @since 6/29/13
 */
@Transactional
@Repository("stateDao")
public class StateDaoImpl implements StateDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public State findById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        return (State) session.get(State.class, id);
    }

    @Override
    public State findByCode(String code) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select i from State i where i.code = :code");
        query.setString("code", code);
        return (State) query.uniqueResult();
    }

    @Override
    public List<State> find() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select i from State i");
        return (List<State>) query.list();
    }

    @Override
    public void save(State issue) {
        Session session = sessionFactory.getCurrentSession();
        session.save(issue);
    }

    @Override
    public void update(State issue) {
        // TODO:

    }

    @Override
    public void remove(State issue) {
        // TODO:

    }
}
