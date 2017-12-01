package services;

import entities.UserEntity;
import java.util.Base64;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

@Service
public class FriendsService extends BaseService{
    
    public ModelAndView loadFriends (HttpServletRequest request) {
        
        UserEntity currentUser = (UserEntity) request.getSession().getAttribute("user");//the connected user
        UserEntity profile = (UserEntity) request.getSession().getAttribute("profile");//the wall user
        ModelAndView mv = this.loadBasePage(request);
        mv.addObject("pageName","friends");
        
        
        List<UserEntity> listOfUsers = this.uDao.findFriends(profile);        
        String message = "";
        
        for (UserEntity us : listOfUsers) {
            
            if(us.isFriendById(currentUser.getId())) {
                message += "<div>deja amis</div>";
            }
            
            if (us.getProfilePic() == null) {
                message += "<img src=\"img/profil.png\" />";
            }
            else {
                if (us.getPictureType().equals("base")) {
                    message += "<img src=\"img/profil.png\" />";
                }
                else if (us.getPictureType().equals("picture")) {
                    us.setBase64Profil(Base64.getEncoder().encodeToString(currentUser.getProfilePic()));
                    message += "<img src=\"data:" + us.getExtprofil() + ";base64," + us.getBase64Profil() + "\" /img>";
                }
                else if (us.getPictureType().equals("drawing")) {
                    byte[] decodedBytes = Base64.getDecoder().decode(us.getProfilePic());
                    us.setBase64Profil(new String(decodedBytes));
                    message += "src=\"data:" + us.getExtprofil() + ";base64," + us.getBase64Profil() + "\" /img>";
                }
            }
        }
        mv.addObject("friendsList",message);
        listOfUsers = this.uDao.findAllUsers();
        message = "";
        
        for (UserEntity us : listOfUsers) {
            
            if(us.isFriendById(currentUser.getId())) {
                message += "<div>deja amis</div>";
            }
            
            if (us.getProfilePic() == null) {
                message += "<img src=\"img/profil.png\" />";
            }
            else {
                if (us.getPictureType().equals("base")) {
                    message += "<img src=\"img/profil.png\" />";
                }
                else if (us.getPictureType().equals("picture")) {
                    us.setBase64Profil(Base64.getEncoder().encodeToString(currentUser.getProfilePic()));
                    message += "<img src=\"data:" + us.getExtprofil() + ";base64," + us.getBase64Profil() + "\" /img>";
                }
                else if (us.getPictureType().equals("drawing")) {
                    byte[] decodedBytes = Base64.getDecoder().decode(us.getProfilePic());
                    us.setBase64Profil(new String(decodedBytes));
                    message += "<img src=\"data:" + us.getExtprofil() + ";base64," + us.getBase64Profil() + "\" /img>";
                }
            }
        }
        mv.addObject("allUsers",message);
        
        return mv;
    }
    
    
    
}
