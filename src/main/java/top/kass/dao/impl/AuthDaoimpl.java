package top.kass.dao.impl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import top.kass.dao.AuthDao;
import top.kass.model.Project;

@Repository
public class AuthDaoimpl implements AuthDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public boolean isProjectCreator(int pid, int uid) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Project as project where id=:id");
        query.setInteger("id", pid);
        if (query.list() == null || query.list().size() == 0) {
            return false;
        }
        Project project = (Project) query.list().get(0);
        if (project.getCreator() == uid) return true;
        return false;
    }

    @Override
    public boolean isProjectUser(int pid, int uid) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createSQLQuery("SELECT * FROM project_user WHERE pid=? AND uid=?");
        if (query.list() == null || query.list().size() == 0) {
            return false;
        }
        return true;
    }
}
