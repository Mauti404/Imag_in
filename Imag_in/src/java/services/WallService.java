package services;

import entities.DAO.MessageDao;
import entities.MessageEntity;
import entities.UserEntity;
import java.util.Base64;
import java.util.Collection;
import java.util.Collections;
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
        Collections.reverse(allCurrentUserMessage);
        String senderPic;
        for (MessageEntity mes : allCurrentUserMessage) {
            System.out.println("test messages");
                UserEntity sender = mes.getSender();
                if (sender.getProfilePic() == null) {
                senderPic="src=\"img/profil.png\"";
                }
                else {
                    if (sender.getPictureType().equals("base")) {
                        senderPic="src=\"img/profil.png\"";
                    }
                    else if (sender.getPictureType().equals("picture")) {
                        sender.setBase64Profil(Base64.getEncoder().encodeToString(sender.getProfilePic()));
                        senderPic= "src=\"data:" + sender.getExtprofil() + ";base64," + sender.getBase64Profil() + "\" alt=\"avatar\"";
                    }
                    else /*if (currentUser.getPictureType().equals("drawing"))*/ {
                         byte[] decodedBytes = Base64.getDecoder().decode(currentUser.getProfilePic());
                        sender.setBase64Profil(new String(decodedBytes));
                        senderPic= "src=\"data:" + sender.getExtprofil() + ";base64," + sender.getBase64Profil() + "\" alt=\"avatar\"";
                    }
                }
            if (mes.getPictureType().equals("picture")) {
                mes.setBase64Content(Base64.getEncoder().encodeToString(mes.getContent()));
                message += "<div class=\"message\">";
                message += "<img style=\"height:40px;width:40px\" "+ senderPic +  " alt=\"avatar\" />";
                message += "<img src=\"data:" + mes.getExtContent() + ";base64," + mes.getBase64Content() + "\" alt=\"avatar\" />";
                message += "</div>";
            }
            else if (mes.getPictureType().equals("drawing")) {
                byte[] decodedBytes = Base64.getDecoder().decode(mes.getContent());
                mes.setBase64Content(new String(decodedBytes));
                message += "<div class=\"message\">";
                message += "<img style=\"height:40px;width:40px\" "+ senderPic +  " alt=\"avatar\" />";
                message += "<img src=\"data:" + mes.getExtContent() + ";base64," + mes.getBase64Content() + "\" alt=\"avatar\" />";
                message += "</div>";
            }
        }   
        
        //mv.addObject("messages",message);//ça marche pas
        mv.addObject("messagesList",message);    
        return mv;
    }
}
