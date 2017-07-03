package bgroup.dao;

import bgroup.model.Amount;
import bgroup.model.SMSCode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

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
