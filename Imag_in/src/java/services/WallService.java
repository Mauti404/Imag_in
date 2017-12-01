package services;

import entities.DAO.MessageDao;
import entities.MessageEntity;
import entities.UserEntity;
import java.util.Base64;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

@Service
public class WallService extends BaseService{
    
    

    @Autowired
    private MessageDao mDao;
    
    public ModelAndView loadWall (HttpServletRequest request) {
        
        UserEntity currentUser = (UserEntity) request.getSession().getAttribute("user");//the connected user
        UserEntity profile = (UserEntity) request.getSession().getAttribute("profile");//the wall user
        ModelAndView mv = this.loadBasePage(request);
        mv.addObject("pageName","wall");
        
        
        List<MessageEntity> allCurrentUserMessage = this.uDao.findMessages(profile);
        String message = "<p>Liste des messages :</p>\n";
        
        for (MessageEntity mes : allCurrentUserMessage) {
            System.out.println("test messages");
            if (mes.getPictureType().equals("picture")) {
                mes.setBase64Content(Base64.getEncoder().encodeToString(mes.getContent()));
                message += "<img src=\"data:" + mes.getExtContent() + ";base64," + mes.getBase64Content() + "\" alt=\"avatar\" />";
            }
            else if (mes.getPictureType().equals("drawing")) {
                byte[] decodedBytes = Base64.getDecoder().decode(mes.getContent());
                mes.setBase64Content(new String(decodedBytes));
                message += "<img src=\"data:" + mes.getExtContent() + ";base64," + mes.getBase64Content() + "\" alt=\"avatar\" />";
            }
        }
        
        //mv.addObject("messages",message);//ça marche pas
        mv.addObject("messagesList",message);    
        return mv;
    }
}
