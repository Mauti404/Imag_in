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
    
    @RequestMapping(value="connect", method=RequestMethod.POST)
    public ModelAndView connect(HttpServletRequest request, HttpServletResponse reponse)
    {
        // on cherche l'utilisateur dans la bdd
        //UserEntity user = uDao.find(user.getId());
        
        
        
        
        // POUR LE MOMENT, ON FAIT L'INSCRIPTION EN MEME TEMPS
        
            
        
        ModelAndView mv = new ModelAndView("wall");
        mv.addObject("userName", "Bonjour " + " id : ");
        return mv;
    }
    
    @RequestMapping(value="launchRegister", method=RequestMethod.POST)
    public ModelAndView launchRegister(HttpServletRequest request, HttpServletResponse reponse)
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
