package controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import entities.DAO.UserDao;
import entities.UserEntity;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;
import org.apache.derby.client.am.SqlException;
import services.WallService;

/**
 *
 * @author mauti
 */

@Controller
public class ConnectionController {
    
    @Autowired
    private UserDao uDao;
    
    @Autowired 
    private WallService wallService;
    
    
    @RequestMapping(value="index", method=RequestMethod.GET)
    public String initIndex()
    {
        return "index";
    }
    
    @RequestMapping(value="connect", method=RequestMethod.POST)
    public ModelAndView connect(HttpServletRequest request, HttpServletResponse reponse)
    {
        // on cherche l'utilisateur dans la bdd
        UserEntity user = this.uDao.findByMail(request.getParameter("login"));
        
        if (user == null) {
            ModelAndView mv = new ModelAndView("index");
            mv.addObject("registerMessage", "<div class=\"alert alert-warning\" role=\"alert\">Mauvais login</div>");
            return mv;
        }
        
        if (user.getPassword().equals(request.getParameter("pass"))) {
            
            // on créé une session
            HttpSession session = request.getSession();
            session.setAttribute("user",user);
            
            if (request == null) {
                System.out.println("test");
            }
            
            // on charge la page par default :  le mur
            ModelAndView mv = this.wallService.loadWall(request);
            
            mv.addObject("messages","LES MESSAGES ICI");
            return mv;
        }
        else {
            ModelAndView mv = new ModelAndView("index");
            mv.addObject("registerMessage", "<div class=\"alert alert-warning\" role=\"alert\">Mauvais mot de passe</div>");
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
        try {
            uDao.save(user);
            ModelAndView mv = new ModelAndView("index");
            mv.addObject("registerMessage", "<div class=\"alert alert-primary\" role=\"alert\">Register succesful !</div>");
            return mv;
        } catch (Exception ex) {
            ModelAndView mv = new ModelAndView("inscription");
            mv.addObject("registerMessage", "<div class=\"alert alert-primary\" role=\"alert\">Erreur lors de la sauvegarde !</div>");
            return mv;
        }
        
        
    }
}
