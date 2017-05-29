package bgroup.dao;

import java.sql.Date;
import java.util.List;

import bgroup.model.CustomUser;
import bgroup.model.User;


public interface UserDao {

    CustomUser findById(int id);

    //User findByUserName(String lastName,String firstName,String secondName,String birthDate, String phone);
    //CustomUser findByPhone(String phone);

    CustomUser findByFio(String lastName, String firstName, String secondName,
                         Date birthDate, String phone);

    //void save(User user);

    //void deleteByUserName(String userName);

    //List<User> findAllUsers();

}

