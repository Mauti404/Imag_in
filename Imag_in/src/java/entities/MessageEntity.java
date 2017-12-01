package entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

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

    @Lob
    @Column
    private byte[] content;
    
    @Column(name="extContent")
    private String extContent;
    
    @Transient
    private String base64Content;
    
    @ManyToOne // receiver
    @JoinColumn(name="receiver")
    private UserEntity receiver;
    
    @ManyToOne // sender
    @JoinColumn(name="sender")
    private UserEntity sender;
    
    @Column
    private String pictureType;

    public MessageEntity() {
    }
    
    public MessageEntity(UserEntity sender, UserEntity receiver) {
        this.sender = sender;
        this.receiver = receiver;
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
    
    public UserEntity getSender() {
        return this.sender;
    }
    
    public UserEntity getReceiver() {
        return this.receiver;
    }

    public byte[] getContent() {
        return content;
    }

    public String getExtContent() {
        return extContent;
    }

    public String getBase64Content() {
        return base64Content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public void setExtContent(String extContent) {
        this.extContent = extContent;
    }

    public void setBase64Content(String base64Content) {
        this.base64Content = base64Content;
    }

    public String getPictureType() {
        return pictureType;
    }

    public void setPictureType(String pictureType) {
        this.pictureType = pictureType;
    }
    /*
    public NotificationEntity getNotification() {
        return notification;
    }

    public void setNotification(NotificationEntity notification) {
        this.notification = notification;
    }
    */

    public void setId(int id) {
        this.id = id;
    }
    
    
}
