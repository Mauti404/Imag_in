
package entities.DAO;

import entities.NotificationEntity;

/**
 *
 * @author M. Durand
 */
public interface NotificationDao {
    public void save(NotificationEntity n);
    public void update(NotificationEntity n);
    public void delete(NotificationEntity n);
}
