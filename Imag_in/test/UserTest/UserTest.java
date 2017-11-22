package UserTest;

import entities.DAO.UserDao;
import entities.DAO.UserDaoImpl;
import entities.UserEntity;
import org.junit.Test;

public class UserTest {
    @Test
    public void insertTest() {
        UserDao udao = new UserDaoImpl();
        UserEntity user1 = new UserEntity("mail_test","password");
        udao.save(user1);
        
        UserEntity user2 = new UserEntity("mail_test2","password2");
        udao.save(user2);
        
        user1.addFriend(user2);
        user2.addFriend(user1);
        
        udao.update(user1);
        
        

    }
}
