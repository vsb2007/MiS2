package bgroup.mysql.dao;

import bgroup.mysql.model.PrintArchive;
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
@Repository("PrintArchiveDao")
public class PrintArchiveDaoImpl extends AbstractDao<Integer, PrintArchive> implements PrintArchiveDao {
    static final Logger logger = LoggerFactory.getLogger(PrintArchiveDaoImpl.class);

    @Override
    public List<PrintArchive> getPrintArchiveListByPhone(String phoneNumber) {
        try {
            Criteria criteria = createEntityCriteria();
            criteria.add(Restrictions.eq("phoneNumber", phoneNumber));
            criteria.add(Restrictions.eq("isDisabled", false));
            criteria.add(Restrictions.eq("isCheckOut", false));
            //criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
            List<PrintArchive> serviceLists = (List<PrintArchive>) criteria.list();
            return serviceLists;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean savePrintArchiveToDB(PrintArchive printArchive) {
        try {
            logger.info(printArchive.getId() + " " + printArchive.getPhoneNumber() + " " + printArchive.getF());
            persist(printArchive);
        } catch (Exception e) {
            logger.error(e.toString());
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
