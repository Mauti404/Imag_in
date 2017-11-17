/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities.DAO;

import entities.MessageEntity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author mauti
 */
public class MessageDaoImpl implements MessageDao {
    
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
    public void save(MessageEntity m) {
        m = em.merge(m);
        em.persist(m);
    }

    @Override
    @Transactional
    public void update(MessageEntity m) {
        em.merge(m);
    }

    @Override
    @Transactional
    public void delete(MessageEntity m) {
        em.remove(m);
    }

    @Override
    @Transactional
    public MessageEntity find(int id) {
        MessageEntity m = em.find(MessageEntity.class, id);
        return m;
    }
    
}
