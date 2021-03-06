package bgroup.oracle.dao;

import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

import bgroup.oracle.model.CustomUser;
import bgroup.oracle.model.Role;
import bgroup.oracle.model.User;
import org.hibernate.Criteria;
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

    @Override
    public CustomUser findById(int id) {
        return null;
    }

    @Override
    public CustomUser findByFio(String lastName, String firstName, String secondName,
                                Date birthDate, String phone) {
        Criteria crit = createEntityCriteria();
        //crit.add(Restrictions.in("cellular", "7" + phone, "8" + phone, phone));
        crit.add(Restrictions.eq("lastName", lastName.toLowerCase()));
        crit.add(Restrictions.eq("firstName", firstName.toLowerCase()));
        crit.add(Restrictions.eq("secondName", secondName.toLowerCase()));
        crit.add(Restrictions.eq("birthDate", birthDate));
        //User user = (User) crit.uniqueResult();
        List<User> users = crit.list();
        if (users == null || users.size() < 1) {
            logger.error("user not found");
            return null;
        }
        User user = findUserByPhone(phone, users);
        if (user == null) {
            logger.error("user not found");
            return null;
        }
        CustomUser customUser = new CustomUser();
        userToCustomUserFunction(customUser, user);
        customUser.setUsername("test22");
        customUser.setPassword("1234");
        customUser.setPhoneAuth(phone);
        Role r = new Role();
        r.setName("ROLE_USER_PRE");
        List<Role> roles = new ArrayList<Role>();
        roles.add(r);
        customUser.setAuthorities(roles);
        return customUser;
    }

    private User findUserByPhone(String phone, List<User> users) {
        for (User user : users) {
            logger.debug("SEX: {}", user.getSex());
            //System.exit(-1);
            String userPhone = getPhoneFromUserPhone(user.getCellular());
            if (userPhone != null) {
                if (userPhone.equals(phone)
                        || userPhone.equals("7" + phone)
                        || userPhone.equals("8" + phone)
                        || userPhone.equals("+7" + phone)
                        ) return user;
            }
        }
        return null;
    }

    private String getPhoneFromUserPhone(String cellular) {
        //String phone = cellular.trim().replaceAll(" |\\-|\\.", "");
        //phone = phone.replaceAll("/[^-0-9]/gim","");
        String phone = cellular.trim().replaceAll("[^0-9]", "");
        return phone;
    }

    private void userToCustomUserFunction(CustomUser customUser, User user) {
        customUser.setBirthDate(user.getBirthDate());
        customUser.setLastName(user.getLastName());
        customUser.setFirstName(user.getFirstName());
        customUser.setSecondName(user.getSecondName());
        customUser.setPhone(user.getPhone());
        customUser.setCellular(user.getCellular());
        customUser.setKeyId(user.getKeyId());
        customUser.setSex(user.getSex());
    }
}
