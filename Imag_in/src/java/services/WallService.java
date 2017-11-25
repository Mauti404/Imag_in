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
        
        UserEntity currentUser = (UserEntity) request.getSession().getAttribute("user");
        
        ModelAndView mv = new ModelAndView("wall");
        mv.addObject("userName",currentUser.getEmail());
        mv.addObject("userConnection",currentUser.getLastConnection());
        
        List<MessageEntity> allCurrentUserMessage = this.uDao.findMessages(currentUser);
        String message = "";
        
        for (MessageEntity mes : allCurrentUserMessage) {
            
            if (mes.getContent() != null) {
                mes.setBase64Content(Base64.getEncoder().encodeToString(mes.getContent()));
                message = message + "<img class=\"\" id=\"\" src=\"data:" + mes.getExtContent() +";base64," + mes.getBase64Content() + "\" alt=\"\">";
            }
        }
        
        mv.addObject("messages",message);
        
        List<UserEntity> allCurrentUserFriend = this.uDao.findFriends(currentUser);
        message = "";
        
        for (UserEntity us : allCurrentUserFriend) {
            message = message + "<div>" + us.getId() + "</div>";
        }
        
        mv.addObject("amis",message);
        
        if (currentUser.getProfilePic() == null) {
            System.out.println("pas d'image de profil");
        }
        else {
            currentUser.setBase64Profil(Base64.getEncoder().encodeToString(currentUser.getProfilePic()));
            mv.addObject("ProfilPic", currentUser.getBase64Profil());
            mv.addObject("Extension", currentUser.getExtprofil());
        }
        
        return mv;
    }
}
