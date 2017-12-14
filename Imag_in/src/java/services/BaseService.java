package services;

import entities.DAO.UserDao;
import entities.UserEntity;
import java.util.Base64;
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
    
    public ModelAndView loadBasePage (HttpServletRequest request) {
        
        UserEntity currentUser = (UserEntity) request.getSession().getAttribute("user");//the connected user
        UserEntity profile = (UserEntity) request.getSession().getAttribute("profile");//the wall user
        ModelAndView mv = new ModelAndView("base");
        
        mv.addObject("userName",currentUser.getEmail());
        mv.addObject("profileName",profile.getEmail());
        mv.addObject("profileConnection",profile.getLastConnection());
        mv.addObject("currentUserId",currentUser.getId());
        
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
        if (profile.getProfilePic() == null) {
            mv.addObject("visitedProfilePict","src=\"img/profil.png\"");
        }
        else {
            if (profile.getPictureType().equals("base")) {
                mv.addObject("visitedProfilePict","src=\"img/profil.png\"");
            }
            else if (profile.getPictureType().equals("picture")) {
                profile.setBase64Profil(Base64.getEncoder().encodeToString(profile.getProfilePic()));
                mv.addObject("visitedProfilePict", "src=\"data:" + profile.getExtprofil() + ";base64," + profile.getBase64Profil() + "\" alt=\"avatar\"");
            }
            else if (profile.getPictureType().equals("drawing")) {
                byte[] decodedBytes = Base64.getDecoder().decode(profile.getProfilePic());
                profile.setBase64Profil(new String(decodedBytes));
                mv.addObject("visitedProfilePict", "src=\"data:" + profile.getExtprofil() + ";base64," + profile.getBase64Profil() + "\" alt=\"avatar\"");
            }
        }
        
        //loading button
        if ((currentUser.getId() != profile.getId()) && (!currentUser.isFriend(profile))) {
            mv.addObject("addFriendButton","<form method=\"POST\" action=\"addFriend.htm\" ><button btn class=\"btn-primary\" type=\"submit\" value=\"Ajouter\" /><input name=\"ami\" type=\"hidden\" value=\"" + profile.getId() + "\" /></form>");
        }
        
        return mv;
    }
}
