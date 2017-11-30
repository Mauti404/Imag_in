package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

/**
 *
 * @author mauti
 */

@Entity
public class UserEntity implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    
    @Column
    private String password;

    @Column
    private String profilPictureURL;
    
    @Column
    private String lastConnection;
    
    @Column(unique=true)
    private String email;
    
    @Lob
    @Column
    private byte[] profilePic;
    
    @Column(name="extprofil")
    private String extprofil;
    
    @Column
    private String pictureType;
    
    @Transient
    private String base64Profil;
    
    @ManyToMany (fetch = FetchType.EAGER,cascade=CascadeType.ALL)
    @JoinTable(name = "friends", 
            joinColumns = @JoinColumn(name = "friend1", referencedColumnName = "id"), 
            inverseJoinColumns = @JoinColumn(name = "friend2", referencedColumnName = "id"))
    private List<UserEntity> friends;
    
    @OneToMany (mappedBy="sender",cascade=CascadeType.ALL,fetch = FetchType.EAGER)
    private List<MessageEntity> messages;
    
    @OneToMany (mappedBy="target",cascade=CascadeType.ALL,fetch = FetchType.EAGER)
    private List<NotificationEntity> notifications;
    
    public UserEntity() {
        this.password = "mot de passe";
        this.lastConnection = "today";
        this.profilPictureURL = "head.png";
        this.email = "test@imagin.com";
        this.messages = new ArrayList<>();
        this.friends = new ArrayList<>();
        this.pictureType = "base";
    }
    
    public UserEntity(String mail,String password) {
        this.password = password;
        this.lastConnection = "today";
        this.profilPictureURL = "head.png";
        this.email = mail;
        this.messages = new ArrayList<>();
        this.friends = new ArrayList<>();
        this.pictureType = "base";
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public String getProfilPictureURL() {
        return profilPictureURL;
    }

    public String getLastConnection() {
        return lastConnection;
    }

    public String getEmail() {
        return email;
    }
    
    public List<MessageEntity> getMessages() {
        return this.messages;
    }
    
    public List<UserEntity> getFriends() {
        return this.friends;
    }

    public void setProfilPictureURL(String profilPictureURL) {
        this.profilPictureURL = profilPictureURL;
    }

    public void setLastConnection(String lastConnection) {
        this.lastConnection = lastConnection;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public void addMessage(MessageEntity m) {
        this.messages.add(m);
    }
    
    public void removeMessage(MessageEntity m) {        
        this.messages.remove(m);
    }    
    
    public void addFriend(UserEntity u) {
        this.friends.add(u);
    }
    
    public void removeFriend(int friendId) {
        UserEntity toRemove = null;
        
        for(UserEntity friend : this.friends) {
            if (friend.getId() == friendId) {
                toRemove = friend;
            }
        }
        
        this.friends.remove(toRemove);
    }
    
    public void addNotification(NotificationEntity ne) {
        this.notifications.add(ne);
    }
    
    public void removeNotification(NotificationEntity ne) {
        this.notifications.remove(ne);
    }
    
    public boolean findFriendById(int friendId) {
        for (UserEntity ue : this.friends) {
            if (ue.getId() == friendId) {
                return true;
            }
        }
        
        return false;
    }

    public byte[] getProfilePic() {
        return profilePic;
    }

    public String getExtprofil() {
        return extprofil;
    }

    public String getBase64Profil() {
        return base64Profil;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public void setProfilePic(byte[] profilePic) {
        this.profilePic = profilePic;
    }

    public void setExtprofil(String extprofil) {
        this.extprofil = extprofil;
    }

    public void setBase64Profil(String base64Profil) {
        this.base64Profil = base64Profil;
    }

    public String getPictureType() {
        return pictureType;
    }

    public void setPictureType(String pictureType) {
        this.pictureType = pictureType;
    }

    public List<NotificationEntity> getNotifications() {
        return notifications;
    }
    
    public void removeMessageById(int idMessage) {
        MessageEntity toRemove = null;
        for (MessageEntity mes : this.messages) {
            if (mes.getId() == idMessage) {
                toRemove = mes;
            }
        }
        
        if (toRemove != null) {
            this.messages.remove(toRemove);
        }
    }
    
    
}
