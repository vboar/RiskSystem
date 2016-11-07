package top.kass.dao.impl;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.internal.util.type.PrimitiveWrapperHelper;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jca.cci.core.InteractionCallback;
import org.springframework.stereotype.Repository;
import top.kass.dao.ProjectDao;
import top.kass.model.Project;
import top.kass.model.User;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ProjectDaoImpl implements ProjectDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Project getById(int id) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Project as project where id=:id");
        query.setInteger("id", id);
        if (query.list() == null || query.list().size() == 0) {
            return null;
        } else {
            return (Project) query.list().get(0);
        }
    }

    @Override
    public Project create(Project project) {
        Session session = sessionFactory.getCurrentSession();
        session.save(project);
        return project;
    }

    @Override
    public Project update(Project project) {
        Session session = sessionFactory.getCurrentSession();
        session.save(project);
        return project;
    }

    @Override
    public void delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("delete Project where id=:id");
        query.setInteger("id", id);
        query.executeUpdate();
    }

    @Override
    public void updateUsers(int id, List<String> uids, int uid) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createSQLQuery("DELETE FROM project_user where pid=:id");
        query.setInteger("id", id);
        query.executeUpdate();
        boolean flag = false;
        for (String s: uids) {
            int tempId = Integer.parseInt(s);
            query = session.createSQLQuery("INSERT INTO project_user (pid, uid) VALUES (?, ?)");
            query.setInteger(0, id);
            query.setInteger(1, tempId);
            query.executeUpdate();
            if (tempId == uid) flag = true;
        }
        if (!flag) {
            query = session.createSQLQuery("INSERT INTO project_user (pid, uid) VALUES (?, ?)");
            query.setInteger(0, id);
            query.setInteger(1, uid);
            query.executeUpdate();
        }

    }

    @Override
    public List<User> getUsers(int id) {
        return null;
    }

    @Override
    public List<Project> getCreatedProjectsByUid(int uid) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Project as project where creator=:uid");
        query.setInteger("uid", uid);
        if (query.list() == null || query.list().size() == 0) {
            return new ArrayList<Project>();
        } else {
            return (List<Project>)query.list();
        }
    }

    @Override
    public List getProjectsByUid(int uid) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createSQLQuery("SELECT * FROM project p LEFT JOIN project_user pu " +
                "ON p.id=pu.pid WHERE pu.uid=?");
        query.setInteger(0, uid);
        query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        List list = query.list();
        if (list == null) list = new ArrayList<>();
        return list;
    }

}
