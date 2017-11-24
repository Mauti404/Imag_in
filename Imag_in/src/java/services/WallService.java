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
            message = message + "<div>" + mes.getContentURL() + "</div>";
        }
        
        mv.addObject("messages",message);
        
        List<UserEntity> allCurrentUserFriend = this.uDao.findFriends(currentUser);
        message = "";
        
        for (UserEntity us : allCurrentUserFriend) {
            message = message + "<div>" + us.getId() + "</div>";
        }
        
        mv.addObject("amis",message);
        
        currentUser.setBase64Profil(Base64.getEncoder().encodeToString(currentUser.getProfilePic()));
        mv.addObject("ProfilPic", currentUser.getBase64Profil());
        mv.addObject("Extension", currentUser.getExtprofil());
        
        return mv;
    }
}
