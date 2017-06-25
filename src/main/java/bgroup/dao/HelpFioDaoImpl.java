package bgroup.dao;

import bgroup.model.Contract;
import bgroup.model.HelpFio;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("helpFioDao")
public class HelpFioDaoImpl extends AbstractDao<Integer, HelpFio> implements HelpFioDao {
    static final Logger logger = LoggerFactory.getLogger(HelpFioDaoImpl.class);

    @Override
    public HelpFio getHelpFio(int PATIENT_ID) {
        Session session = getSession();
        Query query = session.createSQLQuery(
                "SELECT \n" +
                        "       fn_pat_name_by_id(keyid) AS fio, \n" +
                        "       fn_pat_name_by_id(keyid) AS fio2, \n" +
                        "       fn_pat_name_by_id(keyid) AS fio3, \n" +
                        "       fn_pat_name_by_id(keyid) AS fio5, \n" +
                        "(SELECT text FROM lu WHERE keyid = 93116) AS \"license\",\n" +
                        "       fn_pat_name_by_id(keyid) AS fio4,\n" +
                        "\t/*fn_man_by_id (gsp_get_user_id()) AS registrator,*/\n" +
                        "\tSex as pol,\n" +
                        "\tNVL(snils, NULL ) AS snils,\n" +
                        "\tNVL(snils, NULL ) AS snils1,\n" +
                        "\ts_spravka_num.nextval AS N,\n" +
                        "\ts_spravka_num.nextval AS N2,\n" +
                        "       num AS PATNUM,\n" +
                        "       TO_CHAR(SYSDATE,'dd.mm.yyyy') AS GIVEDATE,\n" +
                        "       TO_CHAR(SYSDATE,'dd.mm.yyyy') AS GIVEDATE2\n" +
                        "FROM patient WHERE keyid = :PATIENT_ID\n")
                .setResultTransformer(Transformers.aliasToBean(HelpFio.class))
                ;
        query.setParameter("PATIENT_ID", PATIENT_ID);
        //List<Object[]> result = null;
        List<HelpFio> result = null;
        try {
            result = query.list();
        } catch (Exception e) {
            logger.error(e.toString());
        }
        Contract contract = null;
        if (result != null && result.size() == 1) {
            return result.get(0);
        } else {
            logger.info("ничего не найдено");
        }
        return null;
    }
}
