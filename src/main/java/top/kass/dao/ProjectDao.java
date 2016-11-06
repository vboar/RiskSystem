package top.kass.dao;

import top.kass.model.Project;

public interface ProjectDao {

    public Project getById(int id);

    public Project create(Project project);

    public Project update(Project project);

    public void delete(int id);



}
