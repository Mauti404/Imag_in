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
        String mail = request.getParameter("login");
        String pass = request.getParameter("pass");
        ModelAndView mv = new ModelAndView("wall");
        
        
        // POUR LE MOMENT, ON FAIT L'INSCRIPTION EN MEME TEMPS
        
        UserEntity user = new UserEntity(mail,pass);
        uDao.save(user);
        
        user = uDao.find(user.getId());      
        
        mv.addObject("userName", "Bonjour " + " id : " + user.getId());
        return mv;
    }
}
