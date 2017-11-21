package controllers;

import entities.DAO.MessageDao;
import entities.DAO.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 *
 * @author mauti
 */

@Controller
public class WallController {

    private UserDao uDao;

    private MessageDao mDao;
    
    public void setUdao(UserDao uDao)
    {
        this.uDao = uDao;
    }
    
    public void setMdao(MessageDao mDao)
    {
        this.mDao = mDao;
    }
    
    
}
