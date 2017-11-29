package entities.DAO;

import entities.MessageEntity;
import entities.UserEntity;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.apache.derby.client.am.SqlException;

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
    public void save(UserEntity uOld) throws SqlException
    {
        UserEntity uNew = em.merge(uOld);
        em.persist(uNew);
        uOld.setId(uNew.getId());
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
        
        try {
            return query.setParameter("mail",mail).getSingleResult();
        }
        catch (NoResultException nre) {
            return null;
        }      
    }

    @Override
    @Transactional
    public List<MessageEntity> findMessages(UserEntity user) {
        TypedQuery<MessageEntity> query = this.em.createQuery("SELECT m FROM MessageEntity m WHERE m.receiver = :user ",MessageEntity.class);
        return query.setParameter("user",user).getResultList();
    }
    
    @Override
    @Transactional
    public List<UserEntity> findFriends(UserEntity user) {
        TypedQuery<UserEntity> query = this.em.createQuery("SELECT u FROM UserEntity u WHERE :user MEMBER OF u.friends",UserEntity.class);
        return query.setParameter("user",user).getResultList();
    }
}
