package controllers;

import entities.DAO.MessageDao;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import entities.DAO.UserDao;
import entities.MessageEntity;
import entities.UserEntity;
import java.util.List;
import javax.servlet.http.HttpSession;

/**
 *
 * @author mauti
 */

@Controller
public class ConnectionController {
    
    @Autowired
    private UserDao uDao;
    
    public void setUdao(UserDao uDao)
    {
        this.uDao = uDao;
    }
    
    
    @RequestMapping(value="index", method=RequestMethod.GET)
    public String initIndex()
    {
        return "index";
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
            System.out.println("nb friends : " + us.getFriends().size());
        }
        
        mv.addObject("amis",message);
        
        return mv;
    }
    
    @RequestMapping(value="connect", method=RequestMethod.POST)
    public ModelAndView connect(HttpServletRequest request, HttpServletResponse reponse)
    {
        // on cherche l'utilisateur dans la bdd
        UserEntity user = this.uDao.findByMail(request.getParameter("login"));
        
        if (user.getPassword().equals(request.getParameter("pass"))) {
            
            // on créé une session
            HttpSession session = request.getSession();
            session.setAttribute("user",user);
            
            
            // on charge la page par default :  le mur
            ModelAndView mv = loadWall(request);
            
            mv.addObject("messages","LES MESSAGES ICI");
            return mv;
        }
        else {
            ModelAndView mv = new ModelAndView("index");
            mv.addObject("registerMessage", "<div class=\"alert alert-warning\" role=\"alert\">Register succesful !</div>");
            return mv;
        }
    }
    
    @RequestMapping(value="launchRegister", method=RequestMethod.POST)
    public ModelAndView launchRegister()
    {
        return new ModelAndView("inscription");
    }
    
    @RequestMapping(value="register", method=RequestMethod.POST)
    public ModelAndView register(HttpServletRequest request, HttpServletResponse reponse)
    {
        String mail = request.getParameter("mail");
        String pass = request.getParameter("pass");
        
        UserEntity user = new UserEntity(mail,pass);
        uDao.save(user);
        
        ModelAndView mv = new ModelAndView("index");
        mv.addObject("registerMessage", "<div class=\"alert alert-primary\" role=\"alert\">Register succesful !</div>");
        return mv;
    }
}
