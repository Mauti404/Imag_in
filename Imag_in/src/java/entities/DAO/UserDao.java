package entities.DAO;

import entities.MessageEntity;
import entities.UserEntity;
import java.util.List;
import org.apache.derby.client.am.SqlException;

/**
 *
 * @author mauti
 */
public interface UserDao {
    public void save(UserEntity u) throws SqlException;
    public void update(UserEntity u);
    public void delete(UserEntity u);
    public UserEntity find(int id);
    public UserEntity findByMail(String mail);
    public List<MessageEntity> findMessages(UserEntity user);
    public List<UserEntity> findFriends(UserEntity user);
}
