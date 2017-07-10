package bgroup.oracle.dao;

import bgroup.oracle.model.SMSCode;

/**
 * Created by VSB on 13.06.2017.
 * MiS2
 */
public interface SMSCodeDao {
    public SMSCode createCode();
    public SMSCode findCodeByPhone(String phone);
    public boolean checkOutCode();
    public boolean disableCode();

}
