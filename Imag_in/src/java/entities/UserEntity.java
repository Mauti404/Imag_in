package entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author mauti
 */

@Entity
public class UserEntity implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    private String id;
    
    @Column
    private String password;

    @Column
    private String profilPictureURL;
    
    @Column
    private String lastConnection;
    
    @Column
    private String email;
    
}
