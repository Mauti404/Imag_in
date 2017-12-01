package services;

import entities.DAO.UserDao;
import entities.UserEntity;
import java.util.Base64;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

@Service
public class FriendsService extends BaseService{
    @Autowired
    UserDao udao;
    
    public ModelAndView loadFriends (HttpServletRequest request) {
        
        UserEntity currentUser = (UserEntity) request.getSession().getAttribute("user");//the connected user
        String newUser = request.getParameter("newUser");
        UserEntity profile = udao.find(Integer.parseInt(newUser));
        request.getSession().setAttribute("profile", profile);
        
        
        ModelAndView mv = this.loadBasePage(request);
        mv.addObject("pageName","friends");
        
        
        List<UserEntity> listOfUsers = this.uDao.findFriends(profile);        
        String message = "";
        
        for (UserEntity us : listOfUsers) {
            
            if(us.isFriendById(currentUser.getId())) {
                message += "<div>deja amis</div>";
            }
            
            if (us.getProfilePic() == null) {
                message += "<form method=\"POST\" action=\"getMessage.htm\">";
                message += "<input type=\"image\"  src=\"img/profil.png\" />";
                message += "<input type=\"hidden\" name=\"newUser\" value=\"";
                message += us.getId();
                message += "\"/>";
                message += "</form>";
            }
            else {
                if (us.getPictureType().equals("base")) {
                    message += "<form method=\"POST\" action=\"getMessage.htm\">";
                    message += "<input type=\"image\" src=\"img/profil.png\" />";
                    message += "<input type=\"hidden\" name=\"newUser\" value=\"";
                    message += us.getId();
                    message += "\"/>";
                    message += "</form>";
                }
                else if (us.getPictureType().equals("picture")) {
                    message += "<form method=\"POST\" action=\"getMessage.htm\">";
                    us.setBase64Profil(Base64.getEncoder().encodeToString(currentUser.getProfilePic()));
                    message += "<input type=\"image\"  src=\"data:" + us.getExtprofil() + ";base64," + us.getBase64Profil() + "\" />";
                    message += "<input type=\"hidden\" name=\"newUser\" value=\"";
                    message += us.getId();
                    message += "\"/>";
                    message += "</form>";
                }
                else if (us.getPictureType().equals("drawing")) {
                    message += "<form method=\"POST\" action=\"getMessage.htm\">";
                    byte[] decodedBytes = Base64.getDecoder().decode(us.getProfilePic());
                    us.setBase64Profil(new String(decodedBytes));
                    message += "<input type=\"image\" src=\"data:" + us.getExtprofil() + ";base64," + us.getBase64Profil() + "\" />";
                    message += "<input type=\"hidden\" name=\"newUser\" value=\"";
                    message += us.getId();
                    message += "\"/>";
                    message += "</form>";
                }
            }
        }
        mv.addObject("friendsList",message);
        listOfUsers = this.uDao.findAllUsers();
        message = "";
        
        for (UserEntity us : listOfUsers) {
            
            if(us.isFriendById(currentUser.getId())) {
                
                message += "<div>deja amis</div>";
            }
            
            if (us.getProfilePic() == null) {
                message += "<form method=\"POST\" action=\"getMessage.htm\">";
                message += "<input type=\"image\"  src=\"img/profil.png\" />";
                message += "<input type=\"hidden\" name=\"newUser\" value=\"";
                message += us.getId();
                message += "\"/>";
                message += "</form>";
            }
            else {
                if (us.getPictureType().equals("base")) {
                    message += "<form method=\"POST\" action=\"getMessage.htm\">";
                    message += "<input type=\"image\"  src=\"img/profil.png\" />";
                    message += "<input type=\"hidden\" name=\"newUser\" value=\"";
                    message += us.getId();
                    message += "\"/>";
                    message += "</form>";
                }
                else if (us.getPictureType().equals("picture")) {
                    message += "<form method=\"POST\" action=\"getMessage.htm\">";
                    us.setBase64Profil(Base64.getEncoder().encodeToString(currentUser.getProfilePic()));
                    message += "<input type=\"image\"  src=\"data:" + us.getExtprofil() + ";base64," + us.getBase64Profil() + "\" />";
                    message += "<input type=\"hidden\" name=\"newUser\" value=\"";
                    message += us.getId();
                    message += "\"/>";
                    message += "</form>";
                }
                else if (us.getPictureType().equals("drawing")) {
                    message += "<form method=\"POST\" action=\"getMessage.htm\">";
                    byte[] decodedBytes = Base64.getDecoder().decode(us.getProfilePic());
                    us.setBase64Profil(new String(decodedBytes));
                    message += "<input type=\"image\"  src=\"data:" + us.getExtprofil() + ";base64," + us.getBase64Profil() + "\" />";
                    message += "<input type=\"hidden\" name=\"newUser\" value=\"";
                    message += us.getId();
                    message += "\"/>";
                    message += "</form>";
                }
            }
        }
        mv.addObject("allUsers",message);
        
        return mv;
    }
    
    
    
}
