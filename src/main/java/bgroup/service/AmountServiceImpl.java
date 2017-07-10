package bgroup.service;

import bgroup.oracle.dao.AmountDaoImpl;
import bgroup.oracle.model.Amount;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by VSB on 13.06.2017.
 * MiS2
 */
@Service("amountService")
@Transactional("transactionManager")
public class AmountServiceImpl implements AmountService {
    static final Logger logger = LoggerFactory.getLogger(AmountServiceImpl.class);
    @Autowired
    private AmountDaoImpl amountDao;

    @Override
    public Amount getAmount(int PATIENT_ID, String DATFROM, String DATTO) {
        logger.debug(PATIENT_ID + "," + DATFROM + ", " + DATTO);
        return amountDao.getAmount(PATIENT_ID, DATFROM, DATTO);
    }
}
