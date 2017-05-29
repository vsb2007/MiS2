package bgroup.authentication;

/**
 * Created by VSB on 15.05.2017.
 * MiS3
 */


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.sql.Date;

import bgroup.service.CustomUserService;
import bgroup.service.CustomUserServiceImpl;
import bgroup.model.CustomUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
    static final Logger logger = LoggerFactory.getLogger(CustomAuthenticationProvider.class);

    @Autowired
    private CustomUserService userService;

    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String fio = authentication.getName();
        String password = (String) authentication.getCredentials();

        String lastName = null;
        String firstName = null;
        String secondName = null;
        String birthDateString = null;
        String phone = null;

        logger.debug("FIO String:" + fio);
        String[] fioArray = fio.split("\\|");
        if (fioArray.length < 6) {
            logger.debug("fio: " + fioArray.length);
            throw new BadCredentialsException("Username not found.");
        } else {
            logger.debug("fioArray: ok " + fioArray.length);
            lastName = fioArray[1];
            firstName = fioArray[2];
            secondName = fioArray[3];
            birthDateString = fioArray[4];
            phone = fioArray[5];
        }

        SimpleDateFormat df = new SimpleDateFormat("dd.MM.yy");
        Date birthDate = null;
        try {
            //java.sql.Date sql = new java.sql.Date(parsed.getTime());
            java.util.Date birthDateUtil = df.parse(birthDateString);
            birthDate = new java.sql.Date(birthDateUtil.getTime());
            logger.debug("birthDateUtil:" + birthDateUtil.toString());
            logger.debug("birthDate:" + birthDate.toString());
        } catch (Exception e) {
            e.printStackTrace();
            return new UsernamePasswordAuthenticationToken(null, password, null);
        }

        //CustomUser user = userService.findUserByPhone(phone);
        CustomUser user = null;

        user = userService.findUserByFio(lastName,firstName,secondName,birthDate,phone);

        if (user == null) {
            logger.error("User not found.");
            //throw new BadCredentialsException("Username !!! not found.");
        }
/*
        if (!password.equals(user.getPassword())) {
            logger.error("Wrong Password");
            throw new BadCredentialsException("Wrong password.");
        }
*/
        Collection<? extends GrantedAuthority> authorities = null;
        if (user != null)
            authorities = user.getAuthorities();

        return new UsernamePasswordAuthenticationToken(user, password, authorities);
    }

    public boolean supports(Class<?> arg0) {
        return true;
    }
}
