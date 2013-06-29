package net.canang.acropolis.core.dao;

import net.canang.acropolis.core.model.District;
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
@Repository("districtDao")
public class DistrictDaoImpl implements DistrictDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public District findById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        return (District) session.get(District.class, id);
    }

    @Override
    public List<District> find() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select i from District i");
        return (List<District>) query.list();
    }

    @Override
    public void save(District issue) {
        Session session = sessionFactory.getCurrentSession();
        session.save(issue);
    }

    @Override
    public void update(District issue) {
        // TODO:

    }

    @Override
    public void remove(District issue) {
        // TODO:

    }
}
