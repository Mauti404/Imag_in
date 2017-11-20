package entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
    
    // constructeur par defaut ... bug sans ça
    public UserEntity() {
        this.password = "mot de passe";
        this.lastConnection = "today";
        this.profilPictureURL = "head.png";
        this.email = "test@imagin.com";
    }
    
    public UserEntity(String mail,String password) {
        this.password = password;
        this.lastConnection = "today";
        this.profilPictureURL = "head.png";
        this.email = mail;
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

    public void setProfilPictureURL(String profilPictureURL) {
        this.profilPictureURL = profilPictureURL;
    }

    public void setLastConnection(String lastConnection) {
        this.lastConnection = lastConnection;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
