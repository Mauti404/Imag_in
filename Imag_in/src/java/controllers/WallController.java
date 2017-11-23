package controllers;

import entities.DAO.MessageDao;
import entities.DAO.UserDao;
import entities.MessageEntity;
import entities.UserEntity;
import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
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
        
        currentUser.setBase64Profil(Base64.getEncoder().encodeToString(currentUser.getProfilePic()));
        mv.addObject("ProfilPic", currentUser.getBase64Profil());
        mv.addObject("Extension", currentUser.getExtprofil());
        
        
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
    
    @RequestMapping(value="removeMessage", method=RequestMethod.POST)
    public ModelAndView removeMessage(HttpServletRequest request, HttpServletResponse reponse)
    {        
        return this.loadWall(request);
    }
    
    @RequestMapping(value="addFriend", method=RequestMethod.POST)
    public ModelAndView addFriend(HttpServletRequest request, HttpServletResponse reponse)
    {
        
        UserEntity currentUser = (UserEntity)request.getSession().getAttribute("user");
        UserEntity friend = this.uDao.find(Integer.parseInt(request.getParameter("friend")));
        currentUser.addFriend(friend);
        friend.addFriend(currentUser);
        
        try {
            this.uDao.update(currentUser);
        }
        catch (IllegalStateException ise) {
            System.out.println("ERREUR A GERER : Déjà ami");
        }
        
        
        UserEntity find2 = this.uDao.find(1);
        System.out.println("friend : " + find2.getFriends().size());
        
        return this.loadWall(request);
    }
    
    @RequestMapping(value="removeFriend", method=RequestMethod.POST)
    public ModelAndView removeFriend(HttpServletRequest request, HttpServletResponse reponse)
    {
        
        UserEntity currentUser = (UserEntity)request.getSession().getAttribute("user");
        UserEntity friend = this.uDao.find(Integer.parseInt(request.getParameter("friend")));
        currentUser.removeFriend(friend.getId());
        friend.removeFriend(currentUser.getId());
        
        System.out.println("current " + currentUser.getId() + " nb friends : " + currentUser.getFriends().size());
        System.out.println("friend " + friend.getId() + " nb friends : " + friend.getFriends().size());
        
        this.uDao.update(currentUser);
        this.uDao.update(friend);
        
        return this.loadWall(request);
    }
    
    @RequestMapping(value="addProfilPict", method=RequestMethod.POST)
    public ModelAndView addProfilPict(HttpServletRequest request, HttpServletResponse reponse,@RequestParam("profil_pic") MultipartFile file)
    {
        
        UserEntity currentUser = (UserEntity)request.getSession().getAttribute("user");
        
        try {
            currentUser.setProfilePic((byte[]) file.getBytes());
        } catch (IOException ex) {
            /* ERREUR A GERER */
        }
        currentUser.setExtprofil(file.getContentType());
        
        this.uDao.update(currentUser);
        
        return this.loadWall(request);
    }
}
