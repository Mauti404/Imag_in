/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.DAO.UserDao;
import entities.MessageEntity;
import entities.UserEntity;
import java.util.Base64;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author echo
 */
@Service
public class BaseService {
    @Autowired
    protected UserDao uDao;
    
    public ModelAndView loadPasePage (HttpServletRequest request) {
        
        UserEntity currentUser = (UserEntity) request.getSession().getAttribute("user");//the connected user
        UserEntity profile = (UserEntity) request.getSession().getAttribute("profile");//the wall user
        ModelAndView mv = new ModelAndView("base");
        
        mv.addObject("userName",currentUser.getEmail());
        mv.addObject("profileName",profile.getEmail());
        mv.addObject("profileConnection",profile.getLastConnection());
        
        //userPic
        if (currentUser.getProfilePic() == null) {
            mv.addObject("userProfilePict","src=\"img/profil.png\"");
        }
        else {
            if (currentUser.getPictureType().equals("base")) {
                mv.addObject("userProfilePict","src=\"img/profil.png\"");
            }
            else if (currentUser.getPictureType().equals("picture")) {
                currentUser.setBase64Profil(Base64.getEncoder().encodeToString(currentUser.getProfilePic()));
                mv.addObject("userProfilePict", "src=\"data:" + currentUser.getExtprofil() + ";base64," + currentUser.getBase64Profil() + "\" alt=\"avatar\"");
            }
            else if (currentUser.getPictureType().equals("drawing")) {
                 byte[] decodedBytes = Base64.getDecoder().decode(currentUser.getProfilePic());
                currentUser.setBase64Profil(new String(decodedBytes));
                mv.addObject("userProfilePict", "src=\"data:" + currentUser.getExtprofil() + ";base64," + currentUser.getBase64Profil() + "\" alt=\"avatar\"");
            }
        }
        //profilePic
        if (currentUser.getProfilePic() == null) {
            mv.addObject("visitedProfilePict","src=\"img/profil.png\"");
        }
        else {
            if (currentUser.getPictureType().equals("base")) {
                mv.addObject("visitedProfilePict","src=\"img/profil.png\"");
            }
            else if (currentUser.getPictureType().equals("picture")) {
                currentUser.setBase64Profil(Base64.getEncoder().encodeToString(currentUser.getProfilePic()));
                mv.addObject("visitedProfilePict", "src=\"data:" + currentUser.getExtprofil() + ";base64," + currentUser.getBase64Profil() + "\" alt=\"avatar\"");
            }
            else if (currentUser.getPictureType().equals("drawing")) {
                 byte[] decodedBytes = Base64.getDecoder().decode(currentUser.getProfilePic());
                currentUser.setBase64Profil(new String(decodedBytes));
                mv.addObject("visitedProfilePict", "src=\"data:" + currentUser.getExtprofil() + ";base64," + currentUser.getBase64Profil() + "\" alt=\"avatar\"");
            }
        }
        
        return mv;
    }
}
