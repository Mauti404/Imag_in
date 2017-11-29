package services;

import entities.DAO.MessageDao;
import entities.DAO.UserDao;
import entities.MessageEntity;
import entities.UserEntity;
import java.util.Base64;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

@Service
public class WallService {
    
    @Autowired
    private UserDao uDao;

    @Autowired
    private MessageDao mDao;
    
    public ModelAndView loadWall (HttpServletRequest request) {
        
        UserEntity currentUser = (UserEntity) request.getSession().getAttribute("user");//the connected user
        UserEntity profile = (UserEntity) request.getSession().getAttribute("profile");//the wall user
        ModelAndView mv = new ModelAndView("wall");
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
        
        List<MessageEntity> allCurrentUserMessage = this.uDao.findMessages(profile);
        String message = "<p>Liste des messages :</p>\n";
        
        for (MessageEntity mes : allCurrentUserMessage) {
            System.out.println("test messages");
            if (mes.getPictureType().equals("picture")) {
                mes.setBase64Content(Base64.getEncoder().encodeToString(mes.getContent()));
                message += "<img src=\"data:" + mes.getExtContent() + ";base64," + mes.getBase64Content() + "\" alt=\"avatar\" />";
            }
            else if (mes.getPictureType().equals("drawing")) {
                byte[] decodedBytes = Base64.getDecoder().decode(mes.getContent());
                mes.setBase64Content(new String(decodedBytes));
                message += "<img src=\"data:" + mes.getExtContent() + ";base64," + mes.getBase64Content() + "\" alt=\"avatar\" />";
            }
        }
        
        //mv.addObject("messages",message);//ça marche pas
        mv.addObject("messagesList",message);
        
        List<UserEntity> allCurrentUserFriend = this.uDao.findFriends(profile);
        message = "";
        
        for (UserEntity us : allCurrentUserFriend) {
            message = message + "<div>" + us.getId() + "</div>";
            
            if(us.findFriendById(currentUser.getId())) {
                message += "<div>deja amis</div>";
            }
        }
        
        mv.addObject("amis",message);      
        return mv;
    }
}
