
package entities.DAO;

import entities.NotificationEntity;
import entities.UserEntity;
import java.util.List;

/**
 *
 * @author M. Durand
 */
public interface NotificationDao {
    public void save(NotificationEntity n);
    public void update(NotificationEntity n);
    public void delete(NotificationEntity n);
    public NotificationEntity find(int id);
    public List<NotificationEntity> findNotificationByUser(UserEntity user);
}
