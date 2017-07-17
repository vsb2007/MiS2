package bgroup.mysql.dao;

import bgroup.mysql.model.SmsCode;

import java.util.List;

/**
 * Created by VSB on 13.06.2017.
 * MiS2
 */
public interface SmsCodeDao {
    public List<SmsCode> getSMSCodeListByPhone(String phoneNumber);
    public boolean saveSmsCodeToDB(SmsCode smsCode);
}
