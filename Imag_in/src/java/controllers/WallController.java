package controllers;

import entities.DAO.MessageDao;
import entities.DAO.UserDao;
import entities.MessageEntity;
import entities.UserEntity;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author mauti
 */

@Controller
public class WallController {

    @Autowired
    private UserDao uDao;

    @Autowired
    private MessageDao mDao;
    
    public void setUdao(UserDao uDao)
    {
        this.uDao = uDao;
    }
    
    public void setMdao(MessageDao mDao)
    {
        this.mDao = mDao;
    }
    
    private ModelAndView loadWall (HttpServletRequest request) {
        
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
        
        return mv;
    }
    
    @RequestMapping(value="sendMessage", method=RequestMethod.POST)
    public ModelAndView postMessageToWall(HttpServletRequest request, HttpServletResponse reponse)
    {        
        UserEntity currentUser = (UserEntity) request.getSession().getAttribute("user");
        currentUser.addMessage(new MessageEntity(request.getParameter("message"),currentUser,currentUser));
        this.uDao.update(currentUser);
        
        return this.loadWall(request);
    }
    
    @RequestMapping(value="addFriend", method=RequestMethod.POST)
    public ModelAndView addFriend(HttpServletRequest request, HttpServletResponse reponse)
    {
        request.getParameter("friend");
        
        UserEntity currentUser = (UserEntity)request.getSession().getAttribute("user");
        UserEntity friend = this.uDao.find(Integer.parseInt(request.getParameter("friend")));
        currentUser.addFriend(friend);
        friend.addFriend(currentUser);
        this.uDao.update(currentUser);
        
        return this.loadWall(request);
    }
    
    
    
    
    
}
