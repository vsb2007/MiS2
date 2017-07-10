package bgroup.service;

import bgroup.oracle.model.CustomUser;

import java.sql.Date;

/**
 * Created by VSB on 22.05.2017.
 * MiS2
 */
public interface CustomUserService {
    //CustomUser findUserByPhone(String phone);
    CustomUser findUserByFio(String lastName, String firstName, String secondName, Date birthDate
            , String phone);
}
