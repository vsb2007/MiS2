package bgroup.dao;

import bgroup.model.Contract;
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
@Repository("contractDao")
public class ContractDaoImpl extends AbstractDao<Integer, Contract> implements ContractDao {
    static final Logger logger = LoggerFactory.getLogger(ContractDaoImpl.class);

    @Override
    public Contract getContract(int PATIENT_ID) {
        logger.debug("Start getDog");

        Session session = getSession();
        logger.debug("session:" + session.toString());
        Query query = session.createSQLQuery(
                "SELECT  p.KeyID as keyId" +
                        ",(SELECT text FROM lu WHERE keyid = 93114) AS \"doverennost\"" +
                        ",(SELECT text FROM lu WHERE keyid = 93114) AS \"doverennost2\"" +
                        ",(SELECT text FROM lu WHERE keyid = 93114) AS \"doverennost3\"" +
                        ",(SELECT text FROM lu WHERE keyid = 93114) AS \"doverennost4\"" +
                        ",(SELECT text FROM lu WHERE keyid = 93116) AS \"license\"" +
                        ",(SELECT text FROM lu WHERE keyid = 93116) AS \"license2\"" +
                        ",(SELECT text FROM lu WHERE keyid = 93116) AS \"license3\"" +
                        ",(SELECT text FROM lu WHERE keyid = 93116) AS \"license4\"" +
                        ",fn_pat_name_by_id (p.KeyID) AS \"fio\"" +
                        ",fn_pat_name_by_id (p.KeyID) AS \"fio2\"" +
                        ",fn_pat_name_by_id (p.KeyID) AS \"fio3\"" +
                        ",fn_pat_name_by_id (p.KeyID) AS \"fio4\"" +
                        ",fn_pat_name_by_id (p.KeyID) AS \"fio5\"" +
                        ",fn_pat_name_by_id (p.KeyID) AS \"fio6\"" +
                        ",p.num AS patnum" +
                        ",p.num AS patnum2" +
                        ",pkg_kladr.get_address(P.KeyID,1) AS \"region1\"" +
                        ",pkg_kladr.get_address(P.KeyID,1) AS \"address\"" +
                        ",pkg_kladr.get_address(P.KeyID,1) AS \"address2\"" +
                        ",pkg_kladr.get_address(P.KeyID,1) AS \"address3\"" +
                        ",pkg_kladr.get_address(P.KeyID,1) AS \"address4\"" +
                        ",fn_pat_phone_by_id(p.keyid) AS phone" +
                        ",fn_pat_phone_by_id(p.keyid) AS phone2" +
                        ",decode (p.sex, 1, 'жен.','муж.') AS sex_name" +
                        ",fn_man_by_id (gsp_get_user_id()) AS registrator" +
                        ",fn_man_by_id (gsp_get_user_id()) AS registrator2" +
                        ",fn_man_by_id (gsp_get_user_id()) AS registrator3" +
                        ",fn_man_by_id (gsp_get_user_id()) AS registrator4" +
                        ",fn_man_by_id (gsp_get_user_id()) AS registrator5" +
                        ",TO_CHAR (p.birthdate, 'dd-mm-yyyy') AS birth_date" +
                        ", NVL ((SELECT c.text FROM company c,agr a WHERE a.keyid = p.agrid AND c.keyid = a.companyid),'') AS company" +
                        ", NVL ((SELECT MAX('Серия ' || l.ser || ' N ' || l.code) FROM police l,agr a WHERE a.keyid = p.agrid " +
                        "AND l.patientid = p.keyid AND l.agrid = a.keyid AND NVL (l.status,0) <> 1),'') AS police" +
                        ", '' AS privils\n" +
                        ",P.CELLULAR\n" +
                        "FROM PATIENT p WHERE p.keyid = :PATIENT_ID")
                .setResultTransformer( Transformers.aliasToBean( Contract.class ) )
                //.addEntity(Contract.class)
                ;
        query.setParameter("PATIENT_ID", PATIENT_ID);
        //List<Object[]> result = null;
        List<Contract> result = null;
        try {
            result = query.list();
        } catch (Exception e) {
            logger.error(e.toString());
        }
        Contract contract = null;
        if (result != null && result.size() == 1) {
            logger.info("что-то нашли");
            return result.get(0);
            //for (Object[] row : result) {
          //  for (Contract row : result) {
                //contract = new Contract();
            //    try {
                 //   contract = (Contract) row[0];
                   // for (Object obj : row) {
                     //   System.out.print(obj + "::");
                   // }

       /*         int i = 0;

                    contract.setKeyId(Integer.parseInt(row[i++].toString()));
                    contract.setDoverennost(getRowValue(row[i++]));
                    contract.setDoverennost2(getRowValue(row[i++]));
                    contract.setDoverennost3(getRowValue(row[i++]));
                    contract.setDoverennost4(getRowValue(row[i++]));
                    contract.setLicense(getRowValue(row[i++]));
                    contract.setLicense2(getRowValue(row[i++]));
                    contract.setLicense3(getRowValue(row[i++]));
                    contract.setLicense4(getRowValue(row[i++]));
                    contract.setFio(getRowValue(row[i++]));
                    contract.setFio2(getRowValue(row[i++]));
                    contract.setFio3(getRowValue(row[i++]));
                    contract.setFio4(getRowValue(row[i++]));
                    contract.setFio5(getRowValue(row[i++]));
                    contract.setFio6(getRowValue(row[i++]));
                    contract.setPatnum(getRowValue(row[i++]));
                    contract.setPatnum2(getRowValue(row[i++]));
                    contract.setRegion1(getRowValue(row[i++]));
                    contract.setAddress(getRowValue(row[i++]));
                    contract.setAddress2(getRowValue(row[i++]));
                    contract.setAddress3(getRowValue(row[i++]));
                    contract.setAddress4(getRowValue(row[i++]));
                    contract.setPhone(getRowValue(row[i++]));
                    contract.setPhone2(getRowValue(row[i++]));
                    contract.setSEX_NAME(getRowValue(row[i++]));
                    contract.setRegistrator(getRowValue(row[i++]));
                    contract.setREGISTRATOR2(getRowValue(row[i++]));
                    contract.setREGISTRATOR3(getRowValue(row[i++]));
                    contract.setREGISTRATOR4(getRowValue(row[i++]));
                    contract.setREGISTRATOR5(getRowValue(row[i++]));
                    contract.setBIRTH_DATE(getRowValue(row[i++]));
                    contract.setCOMPANY(getRowValue(row[i++]));
                    contract.setPOLICE(getRowValue(row[i++]));
                    contract.setPRIVILS(getRowValue(row[i++]));
                    contract.setCELLULAR(getRowValue(row[i++]));
                    //System.out.println("\n");
                    */
            //    } catch (Exception e) {
              //      logger.error(e.toString());
                //}

           // }
        } else {
            logger.info("ничего не найдено");
        }
        return null;
    }

    private String getRowValue(Object row) {
        if (row == null) return "(не указаано)";
        else return row.toString();
    }
}
