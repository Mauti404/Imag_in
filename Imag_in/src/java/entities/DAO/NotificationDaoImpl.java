package entities.DAO;

import entities.NotificationEntity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
    public void save(NotificationEntity n) {
        n = em.merge(n);
        em.persist(n);
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
    
}
