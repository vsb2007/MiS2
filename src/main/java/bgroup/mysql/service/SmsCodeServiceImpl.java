package bgroup.mysql.service;

import bgroup.mysql.dao.SmsCodeDaoImpl;
import bgroup.mysql.model.SmsCode;
import bgroup.oracle.model.CustomUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by VSB on 13.06.2017.
 * MiS2
 */
@Service("smsCodeService")
@Transactional("transactionManagerMysql")
public class SmsCodeServiceImpl implements SmsCodeService {
    static final Logger logger = LoggerFactory.getLogger(SmsCodeServiceImpl.class);
    @Autowired
    private SmsCodeDaoImpl smsCodeDao;

    @Override
    public List<SmsCode> getSmsCodeByPhone(String phoneNumber) {
        return smsCodeDao.getSMSCodeListByPhone(phoneNumber);
    }

    @Override
    public boolean checkSmsCode(int code, String phoneNumber) {
        List<SmsCode> smsCodeList = getSmsCodeByPhone(phoneNumber);
        for (SmsCode smsCode : smsCodeList) {
            if (smsCode.getCode() == code) {
                smsCode.setCheckOut(true);
                setAllCodesToDisable(smsCodeList);
                return true;
            }
        }
        return false;
    }

    private void setAllCodesToDisable(List<SmsCode> smsCodeList) {
        for (SmsCode smsCode : smsCodeList) {
            smsCode.setDisabled(true);
            smsCodeDao.saveSmsCodeToDB(smsCode);
        }
    }

    @Override
    public boolean checkSmsCode(HttpServletRequest request, CustomUser user) {
        if (user == null || user.getCellular() == null) return false;
        String codeString = request.getParameter("code");
        Integer code = null;
        try {
            code = Integer.parseInt(codeString);
        } catch (Exception e) {
            logger.debug("Filed check smsCode by:" + user.getCellular() + " code:" + codeString);
            return false;
        }
        if (code == null) return false;
        if (checkSmsCode(code, user.getCellular())) return true;
        return false;
    }

    @Override
    public boolean saveSmsCodeToDb(SmsCode smsCode) {
        try {
            if (!smsCodeDao.saveSmsCodeToDB(smsCode))
                return false;
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
