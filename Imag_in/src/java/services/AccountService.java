package services;

import entities.UserEntity;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

@Service
public class AccountService extends BaseService{
    
    public ModelAndView loadAccount (HttpServletRequest request) {
        
        UserEntity currentUser = (UserEntity) request.getSession().getAttribute("user");//the connected user
        UserEntity profile = (UserEntity) request.getSession().getAttribute("profile");//the wall user
        ModelAndView mv = this.loadBasePage(request);
        mv.addObject("pageName","account");
        //add account params
 
        return mv;
    }
    
    
    
}
