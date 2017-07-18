package bgroup.authentication;

/**
 * Created by VSB on 15.05.2017.
 * MiS3
 */


import java.text.SimpleDateFormat;
import java.util.Collection;
import java.sql.Date;

import bgroup.mysql.model.SmsCode;
import bgroup.mysql.model.SmsSender;
import bgroup.mysql.service.SmsCodeService;
import bgroup.service.CustomUserService;
import bgroup.oracle.model.CustomUser;
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
    @Autowired
    SmsSender smsSender;
    @Autowired
    SmsCodeService smsCodeService;

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
            java.util.Date birthDateUtil = df.parse(birthDateString);
            birthDate = new java.sql.Date(birthDateUtil.getTime());
        } catch (Exception e) {
            logger.error(e.toString());
            return new UsernamePasswordAuthenticationToken(null, password, null);
        }
        CustomUser user = null;
        user = userService.findUserByFio(lastName, firstName, secondName, birthDate, phone);
        if (user == null) {
            logger.error("User not found.");
            //throw new BadCredentialsException("Username !!! not found.");
        }
        Collection<? extends GrantedAuthority> authorities = null;
        if (user != null) {
            authorities = user.getAuthorities();
            try {
                SmsCode smsCode = new SmsCode(user.getCellular());
                if (smsSender.sendSms(user.getCellular(), "код авторизации " + smsCode.getCode())) {
                    smsCodeService.saveSmsCodeToDb(smsCode);
                }
                else {
                    user = null;
                }
            } catch (Exception e) {
                user = null;
                e.printStackTrace();
                logger.error("Нельзя отправить смс");
            }
        }

        return new UsernamePasswordAuthenticationToken(user, password, authorities);
    }

    public boolean supports(Class<?> arg0) {
        return true;
    }
}
