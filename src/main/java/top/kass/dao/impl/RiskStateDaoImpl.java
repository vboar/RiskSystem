package top.kass.dao.impl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import top.kass.dao.RiskStateDao;
import top.kass.model.RiskState;

import java.util.List;

@Repository
public class RiskStateDaoImpl implements RiskStateDao {

    @Autowired
    private SessionFactory sessionFactory;


    @Override
    public RiskState getById(int id) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from RiskState as risk_state where id=:id");
        query.setInteger("id", id);
        if (query.list() == null || query.list().size() == 0) {
            return null;
        } else {
            return (RiskState) query.list().get(0);
        }
    }

    @Override
    public RiskState create(RiskState riskState) {
        Session session = sessionFactory.getCurrentSession();
        session.save(riskState);
        return riskState;
    }

    @Override
    public RiskState update(RiskState riskState) {
        Session session = sessionFactory.getCurrentSession();
        session.save(riskState);
        return riskState;
    }

    @Override
    public void delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("delete RiskState where id=:id");
        query.setInteger("id", id);
        query.executeUpdate();
    }

    @Override
    public List getByRid(int rid) {
        Session session = sessionFactory.getCurrentSession();
        // TODO
        return null;
    }

}
