package entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

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
    
    @ManyToOne // c'est ptêt pas ça
    @JoinColumn(name="id")
    private UserEntity user;
    
    @OneToOne // c'est ptêt pas ça
    @JoinColumn(name="id")
    private UserEntity sender;

    public MessageEntity(String contentURL, UserEntity user, UserEntity sender) {
        this.contentURL = contentURL;
        this.user = user;
        this.sender = sender;
    }

    public void setContentURL(String contentURL) {
        this.contentURL = contentURL;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public void setSender(UserEntity sender) {
        this.sender = sender;
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

    public UserEntity getUser() {
        return user;
    }

    public UserEntity getSender() {
        return sender;
    }
    
    
}
