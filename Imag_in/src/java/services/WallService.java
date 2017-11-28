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
        
        if (currentUser.getProfilePic() == null) {
            mv.addObject("profilPict","<img src=\"img/profil.png\">");
        }
        else {
            if (currentUser.getPictureType().equals("base")) {
                mv.addObject("profilPict","<img src=\"img/profil.png\">");
            }
            else if (currentUser.getPictureType().equals("picture")) {
                currentUser.setBase64Profil(Base64.getEncoder().encodeToString(currentUser.getProfilePic()));
                mv.addObject("profilPict", "<img class=\"\" id=\"\" src=\"data:" + currentUser.getExtprofil() + ";base64," + currentUser.getBase64Profil() + "\" alt=\"avatar\">");
            }
            else if (currentUser.getPictureType().equals("drawing")) {
                 byte[] decodedBytes = Base64.getDecoder().decode(currentUser.getProfilePic());
                currentUser.setBase64Profil(new String(decodedBytes));
                mv.addObject("profilPict", "<img class=\"\" id=\"\" src=\"data:" + currentUser.getExtprofil() + ";base64," + currentUser.getBase64Profil() + "\" alt=\"avatar\">");
            }
        }
        
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
        return mv;
    }
}
