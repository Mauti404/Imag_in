package entities.DAO;

import entities.UserEntity;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author mauti
 */

@Repository
@Transactional
public class UserDaoImpl implements UserDao {
    
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
    public void save(UserEntity u)
    {
        u = em.merge(u);
        em.persist(u);
    }
    
    @Override
    @Transactional
    public void update(UserEntity u)
    {
        em.merge(u);
    }
    
    @Override
    @Transactional
    public void delete(UserEntity u)
    {
        em.remove(u);
    }
    
    @Override
    @Transactional
    public UserEntity find(int id)
    {
        UserEntity u = em.find(UserEntity.class, id);
        return u;
    }
    
    @Override
    @Transactional
    public UserEntity findByMail(String mail) {
        TypedQuery<UserEntity> query = this.em.createQuery("SELECT u FROM UserEntity u WHERE u.email = :mail",UserEntity.class);
        return query.setParameter("mail",mail).getSingleResult();
    }
}
