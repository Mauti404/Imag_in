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
        
        if (currentUser.getProfilePic() == null) {
            mv.addObject("profilPict","src=\"img/profil.png\"");
        }
        else {
            if (currentUser.getPictureType().equals("base")) {
                mv.addObject("profilPict","src=\"img/profil.png\"");
            }
            else if (currentUser.getPictureType().equals("picture")) {
                currentUser.setBase64Profil(Base64.getEncoder().encodeToString(currentUser.getProfilePic()));
                mv.addObject("profilPict", "src=\"data:" + currentUser.getExtprofil() + ";base64," + currentUser.getBase64Profil() + "\" alt=\"avatar\"");
            }
            else if (currentUser.getPictureType().equals("drawing")) {
                 byte[] decodedBytes = Base64.getDecoder().decode(currentUser.getProfilePic());
                currentUser.setBase64Profil(new String(decodedBytes));
                mv.addObject("profilPict", "src=\"data:" + currentUser.getExtprofil() + ";base64," + currentUser.getBase64Profil() + "\" alt=\"avatar\"");
            }
        }
        
        List<MessageEntity> allCurrentUserMessage = this.uDao.findMessages(profile);
        String message = "<p>Liste des messages :</p>\n";
        
        for (MessageEntity mes : allCurrentUserMessage) {
            
            if (mes.getContent() != null) {
                mes.setBase64Content(Base64.getEncoder().encodeToString(mes.getContent()));
                message += "<div>\n";
                message += "<p>Sender : ";
                message += (mes.getSender().getEmail() +"</p>\n");
                message += "<p>Content : ";
                message += ("<img class=\"\" id=\"\" src=\"data:" + mes.getExtContent() +";base64," + mes.getBase64Content() + "\" alt=\"\">" +"</p>\n");//faudrait faire un canva et ecrire directement dedans l'image ...
                message += "</div>";
                
            }
        }
        
        //mv.addObject("messages",message);//ça marche pas
        mv.addObject("messagesList",message);
        
        List<UserEntity> allCurrentUserFriend = this.uDao.findFriends(profile);
        message = "";
        
        for (UserEntity us : allCurrentUserFriend) {
            message = message + "<div>" + us.getId() + "</div>";
        }
        
        mv.addObject("amis",message);      
        return mv;
    }
}
