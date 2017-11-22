package entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author M. Durand
 */

@Entity
public class MessageEntity implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    
    @Column
    private String contentURL;
    
    @ManyToOne // receiver
    @JoinColumn(name="receiver")
    private UserEntity receiver;
    
    @ManyToOne // sender
    @JoinColumn(name="sender")
    private UserEntity sender;

    public MessageEntity() {
        contentURL = "";
    }
    
    public MessageEntity(String contentURL, UserEntity sender, UserEntity receiver) {
        this.contentURL = contentURL;
        this.sender = sender;
        this.receiver = receiver;
    }

    public void setContentURL(String contentURL) {
        this.contentURL = contentURL;
    }

    public void setSender(UserEntity user) {
        this.sender = user;
    }
    
    public void setReceiver(UserEntity user) {
        this.receiver = user;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getId() {
        return id;
    }

    public String getContentURL() {
        return contentURL;
    }

    
    public UserEntity getSender() {
        return this.sender;
    }
    
    public UserEntity getReceiver() {
        return this.receiver;
    }
    
    
    
}
