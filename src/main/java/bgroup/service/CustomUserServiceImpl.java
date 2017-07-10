package bgroup.service;

/**
 * Created by VSB on 15.05.2017.
 * MiS3
 */

import bgroup.oracle.dao.UserDAOImpl;
import bgroup.oracle.model.CustomUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;

@Service("customUserService")
@Transactional("transactionManager")
public class CustomUserServiceImpl implements CustomUserService {
    static final Logger logger = LoggerFactory.getLogger(CustomUserServiceImpl.class);

    @Autowired
    private UserDAOImpl userDao;

    /*
        public CustomUser loadUserByUsername(String username) throws UsernameNotFoundException {
            return userDao.loadUserByUsername(username);
        }
    */
/*
    public CustomUser findUserByPhone(String phone) throws UsernameNotFoundException {
        return userDao.findByPhone(phone);
    }
    */
    @Override
    public CustomUser findUserByFio(String lastName, String firstName, String secondName,
                                    Date birthDate, String phone) {
        return userDao.findByFio(lastName, firstName, secondName, birthDate, phone);
    }
}
