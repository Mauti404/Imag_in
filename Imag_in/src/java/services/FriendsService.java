package services;

import entities.UserEntity;
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
        
        
        List<UserEntity> allCurrentUserFriend = this.uDao.findFriends(profile);        String message = "<p>Liste des amis :</p>\n";
        
        message = "Liste des Amis :";
        
        for (UserEntity us : allCurrentUserFriend) {
            message = message + "<div>" + us.getId() + "</div>";
            
            if(us.isFriendById(currentUser.getId())) {
                message += "<div>deja amis</div>";
            }
        }
        
        mv.addObject("friendsList",message);      
        return mv;
    }
    
    
    
}
