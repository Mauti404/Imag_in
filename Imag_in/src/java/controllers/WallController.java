package controllers;

import entities.DAO.MessageDao;
import entities.DAO.NotificationDao;
import entities.DAO.UserDao;
import entities.MessageEntity;
import entities.NotificationEntity;
import entities.UserEntity;
import java.io.IOException;
import java.util.Base64;
import java.util.List;
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
public class WallController extends baseController {

    @Autowired
    private MessageDao mDao;
    
    @Autowired
    private NotificationDao nDao;
    
    @Autowired 
    private WallService wallService;
    
    @RequestMapping(value="sendMessage", method=RequestMethod.POST)
    public ModelAndView postMessageToWall(HttpServletRequest request, HttpServletResponse reponse)
    {        
        UserEntity currentUser = (UserEntity)request.getSession().getAttribute("user");
        UserEntity profileUser = (UserEntity)request.getSession().getAttribute("profile");
        MessageEntity message = new MessageEntity(currentUser,profileUser);
        System.out.println("message 0 : " + message.getId());
        // on encode l'image dans content
        byte[] encodedBytes = Base64.getEncoder().encode(request.getParameter("hidden_data").substring(22).getBytes());
        message.setContent(encodedBytes);
        message.setPictureType("drawing");
        message.setExtContent("image/png");
        profileUser.addMessage(message);
        // on pousse le message sur la bdd
        this.mDao.save(message);
        // on créé la notification
        System.out.println("message 1 : " + message.getId());
        NotificationEntity notif = new NotificationEntity(profileUser,message);
        profileUser.addNotification(notif);
        this.nDao.save(notif);
        System.out.println("message 2 : " + message.getId());
        List<NotificationEntity> notifs = this.nDao.findNotificationByUser(profileUser);
        
        for (NotificationEntity ne : notifs) {
            System.out.println("ne : " + ne.getTarget().getEmail());
        }        
       
        return this.wallService.loadWall(request);
    }
    
    @RequestMapping(value="removeMessage", method=RequestMethod.POST)
    public ModelAndView removeMessage(HttpServletRequest request, HttpServletResponse reponse)
    {        
        return this.wallService.loadWall(request);
    }
    
    
    
    //move to account
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
