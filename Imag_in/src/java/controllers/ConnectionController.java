package controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import entities.UserDao;
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
        String login=request.getParameter("login");
        String pass = request.getParameter("pass");
        
        /*
            gestion de la connection
        */
        
        ModelAndView mv = new ModelAndView("wall");
        UserEntity u = uDao.find(login);
        
        if(u==null){
            u = new UserEntity();
            uDao.save(u);
            mv.addObject("userName", "Bonjour ");
            return mv;
        }
        mv.addObject("userName", "Bonjour ");
        return mv;
    }
}
