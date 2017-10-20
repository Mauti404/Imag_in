package model;

import java.util.Objects;

/**
 *
 * @author mauti
 */
public class Utilisateur {

    private int id;
    private String lastConnection;
    private String profilPictureURL;
    
    public Utilisateur(int id, String lastConnection, String profilPictureURL) {
        this.id = id;
        this.lastConnection = lastConnection;
        this.profilPictureURL = profilPictureURL;
    }
    
    public int getId() {
        return id;
    }

    public String getLastConnection() {
        return lastConnection;
    }

    public String getProfilPictureURL() {
        return profilPictureURL;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + this.id;
        hash = 79 * hash + Objects.hashCode(this.lastConnection);
        hash = 79 * hash + Objects.hashCode(this.profilPictureURL);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Utilisateur other = (Utilisateur) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    
    
    

    
}
