package controllers;

import entities.DAO.MessageDao;
import entities.DAO.UserDao;
import entities.MessageEntity;
import entities.UserEntity;
import java.io.IOException;
import java.util.Base64;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import services.WallService;

/**
 *
 * @author M. Durand
 */

@Controller
public class WallController {

    @Autowired
    private UserDao uDao;

    @Autowired
    private MessageDao mDao;
    
    @Autowired 
    private WallService wallService;
    
    @RequestMapping(value="sendMessage", method=RequestMethod.POST)
    public ModelAndView postMessageToWall(HttpServletRequest request, HttpServletResponse reponse)
    {        
        UserEntity currentUser = (UserEntity)request.getSession().getAttribute("user");
        UserEntity profileUser = (UserEntity)request.getSession().getAttribute("profile");
        MessageEntity message = new MessageEntity(currentUser,profileUser);
        
        byte[] encodedBytes = Base64.getEncoder().encode(request.getParameter("hidden_data").substring(22).getBytes());
        message.setContent(encodedBytes);
        message.setPictureType("drawing");
        message.setExtContent("image/png");
        profileUser.addMessage(message);
        
        this.mDao.save(message);

        return this.wallService.loadWall(request);
    }
    
    @RequestMapping(value="removeMessage", method=RequestMethod.POST)
    public ModelAndView removeMessage(HttpServletRequest request, HttpServletResponse reponse)
    {        
        return this.wallService.loadWall(request);
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
        
        return this.wallService.loadWall(request);
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
        
        return this.wallService.loadWall(request);
    }
    
    @RequestMapping(value="addProfilPict", method=RequestMethod.POST)
    public ModelAndView addProfilPict(HttpServletRequest request, HttpServletResponse reponse,@RequestParam("profil_pic") MultipartFile file)
    {
        
        UserEntity currentUser = (UserEntity)request.getSession().getAttribute("user");
        
        try {
            currentUser.setProfilePic((byte[]) file.getBytes());
        } catch (IOException ex) {
            /* ERREUR A GERER */
            System.out.println("erreur");
        }
        currentUser.setExtprofil(file.getContentType());
        currentUser.setPictureType("picture");
        this.uDao.update(currentUser);
        
        return this.wallService.loadWall(request);
    }
    
    @RequestMapping(value="testCanvas", method=RequestMethod.POST)
    public ModelAndView testCanvas(HttpServletRequest request, HttpServletResponse reponse)
    {
        
        UserEntity currentUser = (UserEntity)request.getSession().getAttribute("user");
        
        byte[] encodedBytes = Base64.getEncoder().encode(request.getParameter("hidden_data").substring(22).getBytes());
        currentUser.setProfilePic(encodedBytes);
        currentUser.setPictureType("drawing");
        this.uDao.update(currentUser);
        
        
        return this.wallService.loadWall(request);
    }
}
