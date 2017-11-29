package entities.DAO;

import entities.MessageEntity;
import entities.NotificationEntity;
import entities.UserEntity;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author M. Durand
 */

@Repository
@Transactional
public class NotificationDaoImpl implements NotificationDao{
    
    @PersistenceContext
    private EntityManager em;
    
    public EntityManager getEm() {
        return em;
    }
    
    public void setEm(EntityManager em) 
    {
        this.em = em;
    }

    @Override
    @Transactional
    public void save(NotificationEntity nOld) {
        NotificationEntity nNew = em.merge(nOld);
        em.persist(nNew);
        nOld.setId(nNew.getId());
    }

    @Override
    @Transactional
    public void update(NotificationEntity n) {
        em.merge(n);
    }

    @Override
    @Transactional
    public void delete(NotificationEntity n) {
        em.remove(n);
    }
    
    @Override
    @Transactional
    public NotificationEntity find(int id) {
        NotificationEntity m = em.find(NotificationEntity.class, id);
        return m;
    }
    
    @Override
    @Transactional
    public List<NotificationEntity> findNotificationByUser(UserEntity user) {
        TypedQuery<NotificationEntity> query = this.em.createQuery("SELECT n FROM NotificationEntity n WHERE n.target = :user ",NotificationEntity.class);
        return query.setParameter("user",user).getResultList();
    }
    
}
