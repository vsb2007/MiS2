package bgroup.oracle.dao;

import bgroup.oracle.model.SMSCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

/**
 * Created by VSB on 13.06.2017.
 * MiS2
 */
@Repository("smsCodeDao")
public class SMSCodeDaoImpl extends AbstractDao<Integer, SMSCode> implements SMSCodeDao {
    static final Logger logger = LoggerFactory.getLogger(SMSCodeDaoImpl.class);

    @Override
    public SMSCode createCode() {
        return null;
    }

    @Override
    public SMSCode findCodeByPhone(String phone) {
        return null;
    }

    @Override
    public boolean checkOutCode() {
        return false;
    }

    @Override
    public boolean disableCode() {
        return false;
    }
}
