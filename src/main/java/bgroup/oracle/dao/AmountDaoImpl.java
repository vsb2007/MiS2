package bgroup.oracle.dao;

import bgroup.oracle.model.Amount;
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
@Repository("amountDao")
public class AmountDaoImpl extends AbstractDao<Integer, Amount> implements AmountDao {
    static final Logger logger = LoggerFactory.getLogger(AmountDaoImpl.class);

    @Override
    public Amount getAmount(int PATIENT_ID, String DATFROM, String DATTO) {
        Session session = getSession();
        Query query = session.createSQLQuery(
                "SELECT t.amount || '  ('|| gsp_first_make_upper(number2word(amount)) || ') ' AS amount,\n" +
                        "       t.amount || '  ('|| gsp_first_make_upper(number2word(amount)) || ') ' AS amount2\n" +
                        "FROM        \n" +
                        "(SELECT TO_NUMBER(SUM(amount)) AS amount\n" +
                        "FROM payserv WHERE dat >= gsp_char_2date(:DATFROM)\n" +
                        "               AND dat <= gsp_char_2date(:DATTO)\n" +
                        "AND NVL(pay_return_status,0) != 1\n" +
                        "AND patientid = :PATIENT_ID\n" +
                        ")t\n")
                .setResultTransformer(Transformers.aliasToBean(Amount.class));
        query.setParameter("PATIENT_ID", PATIENT_ID);
        query.setParameter("DATFROM", DATFROM);
        query.setParameter("DATTO", DATTO);
        List<Amount> result = null;
        try {
            result = query.list();
        } catch (Exception e) {
            logger.error(e.toString());
        }
        if (result != null && result.size() == 1) {
            logger.info("что-то нашли");
            return result.get(0);
        } else {
            logger.info("ничего не найдено");
        }
        return null;
    }
}
