package top.kass.dao;

public interface AuthDao {

    public boolean isProjectCreator(int pid, int uid);

    public boolean isProjectUser(int pid, int uid);

}
