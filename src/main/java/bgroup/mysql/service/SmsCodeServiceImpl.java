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
import java.util.Date;
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
        if (smsCodeList == null) return false;
        boolean flag = false;
        for (SmsCode smsCode : smsCodeList) {
            if (smsCode.getCode() == code) {
                try {
                    smsCode.setCheckOut(true);
                    smsCode.setDateChecked(new Date());
                    //setAllCodesToDisable(smsCodeList);
                } catch (Exception e) {
                    e.printStackTrace();
                    return false;
                }
                flag = true;
                break;
            }
        }
        if (flag) {
            setAllCodesToDisable(smsCodeList);
            return true;
        }
        return false;
    }

    private void setAllCodesToDisable(List<SmsCode> smsCodeList) {
        for (SmsCode smsCode : smsCodeList) {
            try {
                smsCode.setDisabled(true);
                //smsCode.setCheckOut(smsCode.getCheckOut());
                logger.debug("getCheckOut " + smsCode.isCheckOut());
                //smsCodeDao.saveSmsCodeToDB(smsCode);
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
    }

    @Override
    public boolean checkSmsCode(HttpServletRequest request, CustomUser customUser) {
        if (customUser == null || customUser.getCellular() == null) return false;
        String codeString = request.getParameter("code");
        Integer code = null;
        try {
            code = Integer.parseInt(codeString);
        } catch (Exception e) {
            logger.debug("Filed check smsCode by:" + customUser.getPhoneAuth() + " code:" + codeString);
            return false;
        }
        if (code == null) return false;
        if (checkSmsCode(code, customUser.getPhoneAuth())) return true;
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
