package bgroup.dao;

import java.util.ArrayList;
import java.util.List;

import bgroup.configuration.AppConfig;
import bgroup.model.CustomUser;
import bgroup.model.Role;
import bgroup.model.User;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;


/**
 * Created by VSB on 15.05.2017.
 * MiS3
 */
@Repository("userDao")
public class UserDAOImpl extends AbstractDao<Integer, User> implements UserDao {
    static final Logger logger = LoggerFactory.getLogger(UserDAOImpl.class);

  /*  public CustomUser loadUserByUsername(final String username) {
        //Write your DB call code to get the user details from DB,But I am just hard coding the user
        logger.debug("loadUserByUsername: in");
        CustomUser user = new CustomUser();
        user.setFirstName("kb");
        user.setLastName("gc");
        user.setUsername("kb");
        user.setPassword("1234");
        Role r = new Role();
        r.setName("ROLE_USER");
        List<Role> roles = new ArrayList<Role>();
        roles.add(r);
        user.setAuthorities(roles);
        logger.debug("loadUserByUsername: " + user.getAuthorities().get(0).getName());
        return user;
    }
*/
    @Override
    public CustomUser findById(int id) {
        return null;
    }

    @Override
    public CustomUser findByPhone(String phone) {
        logger.debug("start findByPhone:" + phone);
        Criteria crit = createEntityCriteria();
        logger.debug("crit ok");
        crit.add(Restrictions.eq("cellular", phone));
        User user = (User) crit.uniqueResult();
        logger.debug("user ok");
        if (user == null) {
            logger.error("user not found");
            return null;
        }
        logger.debug("user not null");

        //CustomUser customUser = (CustomUser) user; // - почему-то не работает - надо вспомниить теорию
        CustomUser customUser = new CustomUser();
        userToCustomUserFunction(customUser, user);

        customUser.setUsername("test22");
        customUser.setPassword("1234");

        Role r = new Role();
        r.setName("ROLE_USER");
        List<Role> roles = new ArrayList<Role>();
        roles.add(r);
        customUser.setAuthorities(roles);


        /*
        if(user!=null){
            Hibernate.initialize(user.getUserProfiles());
        }*/
        return customUser;
    }

    @Override
    public CustomUser findByFio(String phone) {
        return null;
    }

    private void userToCustomUserFunction(CustomUser customUser, User user) {

        customUser.setBirthDate(user.getBirthDate());
        customUser.setLastName(user.getLastName());
        customUser.setFirstName(user.getFirstName());
        customUser.setSecondName(user.getSecondName());
        customUser.setPhone(user.getPhone());
        customUser.setCellular(user.getCellular());
        customUser.setKeyId(user.getKeyId());

    }
}
