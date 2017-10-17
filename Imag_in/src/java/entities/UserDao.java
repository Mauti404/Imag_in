package entities;

/**
 *
 * @author mauti
 */
public interface UserDao {
    public void save(UserEntity u);
    public void update(UserEntity u);
    public void delete(UserEntity u);
    public UserEntity find(String login);
}
