package entities;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 *
 * @author mauti
 */

@Entity
public class NotificationEntity implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    
    
    @OneToOne (cascade={CascadeType.ALL})
    @JoinColumn(name="message")
    private MessageEntity message;
    
    @ManyToOne
    @JoinColumn(name="target")
    private UserEntity target;
    
    public NotificationEntity() {
        
    }
    
    public NotificationEntity(UserEntity target, MessageEntity mes) {
        this.target = target;
        this.message = mes;
    }
    
    
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getId() {
        return id;
    }
    
    public MessageEntity getMessage() {
        return message;
    }

    public void setMessage(MessageEntity message) {
        this.message = message;
    }

    public void setId(int id) {
        this.id = id;
    }

    

    public UserEntity getTarget() {
        return target;
    }

    public void setTarget(UserEntity target) {
        this.target = target;
    }
}
