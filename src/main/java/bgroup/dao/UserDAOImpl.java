package bgroup.dao;

import java.util.ArrayList;
import java.sql.Date;
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

    @Override
    public CustomUser findById(int id) {
        return null;
    }

    @Override
    public CustomUser findByFio(String lastName, String firstName, String secondName,
                                Date birthDate, String phone) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("cellular", phone));
        crit.add(Restrictions.eq("lastName", lastName.toLowerCase()));
        crit.add(Restrictions.eq("firstName", firstName.toLowerCase()));
        crit.add(Restrictions.eq("secondName", secondName.toLowerCase()));
        crit.add(Restrictions.eq("birthDate", birthDate));
        User user = (User) crit.uniqueResult();
        if (user == null) {
            logger.error("user not found");
            return null;
        }
        CustomUser customUser = new CustomUser();
        userToCustomUserFunction(customUser, user);
        customUser.setUsername("test22");
        customUser.setPassword("1234");
        Role r = new Role();
        r.setName("ROLE_USER_PRE");
        List<Role> roles = new ArrayList<Role>();
        roles.add(r);
        customUser.setAuthorities(roles);
        return customUser;
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
