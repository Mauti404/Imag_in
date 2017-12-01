package services;

import entities.DAO.MessageDao;
import entities.DAO.UserDao;
import entities.MessageEntity;
import entities.UserEntity;
import java.util.Base64;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Service
public class FriendsService extends BaseService{
    
    public ModelAndView loadFriends (HttpServletRequest request) {
        
        UserEntity currentUser = (UserEntity) request.getSession().getAttribute("user");//the connected user
        UserEntity profile = (UserEntity) request.getSession().getAttribute("profile");//the wall user
        ModelAndView mv = this.loadBasePage(request);
        mv.addObject("pageName","friends");
        
        
        List<UserEntity> allCurrentUserFriend = this.uDao.findFriends(profile);        String message = "<p>Liste des amis :</p>\n";
        
        message = "Liste des Amis :";
        
        for (UserEntity us : allCurrentUserFriend) {
            message = message + "<div>" + us.getId() + "</div>";
            
            if(us.findFriendById(currentUser.getId())) {
                message += "<div>deja amis</div>";
            }
        }
        
        mv.addObject("friendsList",message);      
        return mv;
    }
    
    
    
}
