package entities.DAO;

import entities.MessageEntity;

/**
 *
 * @author M. Durand
 */
public interface MessageDao {
    public void save(MessageEntity m);
    public void update(MessageEntity m);
    public void delete(MessageEntity m);
    public MessageEntity find(int id);
}
