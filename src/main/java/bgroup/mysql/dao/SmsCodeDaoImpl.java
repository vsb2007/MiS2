package bgroup.mysql.dao;

import bgroup.mysql.model.SmsCode;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by VSB on 13.06.2017.
 * MiS2
 */
@Repository("smsCodeDao")
public class SmsCodeDaoImpl extends AbstractDao<Integer, SmsCode> implements SmsCodeDao {
    static final Logger logger = LoggerFactory.getLogger(SmsCodeDaoImpl.class);

    @Override
    public List<SmsCode> getSMSCodeListByPhone(String phoneNumber) {
        try {
            Criteria criteria = createEntityCriteria();
            criteria.add(Restrictions.eq("phoneNumber", phoneNumber));
            criteria.add(Restrictions.eq("isDisabled", false));
            criteria.add(Restrictions.eq("isCheckOut", false));
            //criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
            List<SmsCode> serviceLists = (List<SmsCode>) criteria.list();
            return serviceLists;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean saveSmsCodeToDB(SmsCode smsCode) {
        try {
            logger.info(smsCode.getId() + " " + smsCode.getPhoneNumber() + " " + smsCode.isCheckOut() + " " + smsCode.isDisabled());
            persist(smsCode);
        } catch (Exception e) {
            logger.error(e.toString());
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
