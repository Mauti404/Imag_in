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
    
    @RequestMapping(value="sendMessage", method=RequestMethod.POST)
    public ModelAndView postMessageToWall(HttpServletRequest request, HttpServletResponse reponse)
    {
        request.getParameter("message");
        
        UserEntity currentUser = (UserEntity) request.getSession().getAttribute("user");
        currentUser.addMessage(new MessageEntity("noImage",currentUser,currentUser));
        this.uDao.update(currentUser);
        
        ModelAndView mv = new ModelAndView("wall");
        mv.addObject("userName",currentUser.getEmail());
        mv.addObject("userConnection",currentUser.getLastConnection());

        String message = "";
        
        List<MessageEntity> allCurrentUserMessage = this.uDao.findMessages(currentUser);
        
        for (MessageEntity mes : allCurrentUserMessage) {
            message = message + "<div>" + mes.getContentURL() + "</div>";
        }
        
        mv.addObject("messages",message);
        return mv;
    }
    
    /*
        Need une méthode loadWall
    */
    
}
