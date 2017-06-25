package bgroup.dao;

import bgroup.model.Contract;
import bgroup.model.ServDate;
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
@Repository("servDateDao")
public class ServDateDaoImpl extends AbstractDao<Integer, ServDate> implements ServDateDao {
    static final Logger logger = LoggerFactory.getLogger(ServDateDaoImpl.class);

    @Override
    public ServDate getServDate(int PATIENT_ID, String DATFROM, String DATTO) {
        Session session = getSession();
        Query query = session.createSQLQuery(
                "SELECT \n" +
                        "\tNVL(TO_CHAR(MAX(TRUNC(dat)),'dd.mm.yyyy'),'________________') AS \"servdate\",\n" +
                        "\tNVL(TO_CHAR(MAX(TRUNC(dat)),'dd.mm.yyyy'),'________________') AS \"servdate2\",\n" +
                        "/* добавил мин даты оплаты - НЕ ИСПОЛЬЗУЕМ*/\n" +
                        "\tNVL(TO_CHAR(MIN(TRUNC(dat)),'dd.mm.yyyy'),'________________') AS \"minservdate\",\n" +
                        "\tNVL(TO_CHAR(MIN(TRUNC(dat)),'dd.mm.yyyy'),'________________') AS \"minservdate2\",\n" +
                        "/* добавил мин даты оплаты - НЕ ИСПОЛЬЗУЕМ*/\n" +
                        "/* проверка на совпадение дат от и до*/\n" +
                        "CASE\n" +
                        "\tWHEN TO_CHAR(MAX(TRUNC(dat)),'dd.mm.yyyy') = TO_CHAR(MIN(TRUNC(dat)),'dd.mm.yyyy') THEN TO_CHAR(MIN(TRUNC(dat)),'dd.mm.yyyy')\n" +
                        "\tELSE TO_CHAR(MIN(TRUNC(dat)),'dd.mm.yyyy') || ' - ' || TO_CHAR(MAX(TRUNC(dat)),'dd.mm.yyyy')\n" +
                        "END \n" +
                        "AS ONEDATE,\n" +
                        "\n" +
                        "CASE\n" +
                        "\tWHEN TO_CHAR(MAX(TRUNC(dat)),'dd.mm.yyyy') = TO_CHAR(MIN(TRUNC(dat)),'dd.mm.yyyy') THEN TO_CHAR(MIN(TRUNC(dat)),'dd.mm.yyyy')\n" +
                        "\tELSE TO_CHAR(MIN(TRUNC(dat)),'dd.mm.yyyy') || ' - ' || TO_CHAR(MAX(TRUNC(dat)),'dd.mm.yyyy')\n" +
                        "END \n" +
                        "AS ONEDATE2\n" +
                        "/* проверка на совпадение дат от и до*/\n" +
                        "\n" +
                        "FROM payserv WHERE dat >= gsp_char_2date(:DATFROM)\n" +
                        "               AND dat <= gsp_char_2date(:DATTO)\n" +
                        "AND patientid = :PATIENT_ID\n" +
                        "AND NVL(pay_return_status,0) != 1\n")
                .setResultTransformer(Transformers.aliasToBean(ServDate.class));
        query.setParameter("PATIENT_ID", PATIENT_ID);
        query.setParameter("DATFROM", DATFROM);
        query.setParameter("DATTO", DATTO);
        List<ServDate> result = null;
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
