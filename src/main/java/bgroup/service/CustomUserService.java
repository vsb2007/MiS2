package bgroup.service;

import bgroup.model.CustomUser;

/**
 * Created by VSB on 22.05.2017.
 * MiS2
 */
public interface CustomUserService {
    CustomUser findUserByPhone(String phone);
}
