package bgroup.mysql.service;

import bgroup.mysql.model.SmsCode;
import bgroup.oracle.model.Amount;
import bgroup.oracle.model.CustomUser;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by VSB on 13.06.2017.
 * MiS2
 */
public interface SmsCodeService {
    public List<SmsCode> getSmsCodeByPhone(String phoneNumber);
    public boolean checkSmsCode(int code, String phoneNumber);
    public boolean saveSmsCodeToDb(SmsCode smsCode);

    boolean checkSmsCode(HttpServletRequest request, CustomUser user);
}
