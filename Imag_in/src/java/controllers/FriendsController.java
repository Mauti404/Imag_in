package controllers;

import entities.DAO.MessageDao;
import entities.DAO.UserDao;
import entities.MessageEntity;
import entities.UserEntity;
import java.io.IOException;
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
import services.FriendsService;
/**
 *
 * @author echo
 */
@Controller
public class FriendsController {
    @Autowired
    private UserDao uDao;

    
    
    @Autowired 
    private FriendsService friendsService;
    
    @RequestMapping(value="getFriendsList", method=RequestMethod.POST)
    public ModelAndView getFriendsList(HttpServletRequest request, HttpServletResponse reponse,@RequestParam("profil_pic") MultipartFile file)
    {        
        UserEntity currentUser = (UserEntity) request.getSession().getAttribute("user");
        UserEntity profileUser = (UserEntity) request.getSession().getAttribute("profile");
        
        MessageEntity message = new MessageEntity(currentUser,currentUser);
        try {
            message.setContent((byte[]) file.getBytes());
        } catch (IOException ex) {
            /* A GERER */
            System.out.println("Pas d'image");
        }
        message.setExtContent(file.getContentType());
        currentUser.addMessage(message);
        this.uDao.update(currentUser);
        ModelAndView mv = new ModelAndView("base");
        
        return mv;
    }
    
    @RequestMapping(value="addFriend", method=RequestMethod.POST)
    public ModelAndView addFriend(HttpServletRequest request, HttpServletResponse reponse)
    {
        
        UserEntity currentUser = (UserEntity)request.getSession().getAttribute("user");
        UserEntity friend = this.uDao.find(Integer.parseInt(request.getParameter("ami")));
        currentUser.addFriend(friend);
        friend.addFriend(currentUser);
        
        try {
            this.uDao.update(currentUser);
        }
        catch (IllegalStateException ise) {
            System.out.println("ERREUR A GERER : Déjà ami");
        }
        
        UserEntity find2 = this.uDao.find(1);
        
        return this.friendsService.loadFriends(request);
    }
    
    
    @RequestMapping(value="removeFriend", method=RequestMethod.POST)
    public ModelAndView removeFriend(HttpServletRequest request, HttpServletResponse reponse)
    {
        
        UserEntity currentUser = (UserEntity)request.getSession().getAttribute("user");
        UserEntity friend = this.uDao.find(Integer.parseInt(request.getParameter("friend")));
        currentUser.removeFriend(friend.getId());
        friend.removeFriend(currentUser.getId());
        
        this.uDao.update(currentUser);
        this.uDao.update(friend);
        
        return this.friendsService.loadFriends(request);
    }
}
