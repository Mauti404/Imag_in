package entities.DAO;

import entities.UserEntity;

/**
 *
 * @author mauti
 */
public interface UserDao {
    public void save(UserEntity u);
    public void update(UserEntity u);
    public void delete(UserEntity u);
    public UserEntity find(int id);
    public UserEntity findByMail(String mail);
}
