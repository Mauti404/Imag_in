package controllers;

import entities.DAO.MessageDao;
import entities.DAO.UserDao;
import entities.MessageEntity;
import entities.UserEntity;
import java.io.IOException;
import java.util.Base64;
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
import services.AccountService;
import services.FriendsService;
/**
 *
 * @author echo
 */
@Controller
public class AccountController{
    @Autowired
    private UserDao uDao;

    
    
    @Autowired 
    private AccountService accountService;
    
    @RequestMapping(value="getAccount", method=RequestMethod.POST)
    public ModelAndView getAccount(HttpServletRequest request, HttpServletResponse reponse)
    {   
        //todo
        UserEntity currentUser = (UserEntity) request.getSession().getAttribute("user");
        request.getSession().setAttribute("profile", currentUser);//force redirect to the current userProfilePage
        UserEntity profileUser = (UserEntity) request.getSession().getAttribute("profile");
        ModelAndView mv = this.accountService.loadAccount(request);
        
        return mv;
    }
    
    //move to account
    @RequestMapping(value="drawProfilPict", method=RequestMethod.POST)
    public ModelAndView addProfilPict(HttpServletRequest request, HttpServletResponse reponse)
    {
        
        UserEntity currentUser = (UserEntity)request.getSession().getAttribute("user");
        byte[] encodedBytes = Base64.getEncoder().encode(request.getParameter("hidden_data").substring(22).getBytes());

        currentUser.setProfilePic(encodedBytes); /* ERREUR A GERER */
        currentUser.setExtprofil("image/png");
        currentUser.setPictureType("drawing");
        this.uDao.update(currentUser);
        
        return this.accountService.loadAccount(request);
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
        
        return this.accountService.loadAccount(request);
    }

}
