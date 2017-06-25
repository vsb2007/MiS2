package bgroup.service;

import bgroup.dao.ServDateDaoImpl;
import bgroup.model.ServDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by VSB on 13.06.2017.
 * MiS2
 */
@Service("servDateService")
@Transactional
public class ServDateServiceImpl implements ServDateService {
    static final Logger logger = LoggerFactory.getLogger(ServDateServiceImpl.class);
    @Autowired
    private ServDateDaoImpl servDateDao;

    @Override
    public ServDate getServDate(int PATIENT_ID, String DATFROM, String DATTO) {
        return servDateDao.getServDate(PATIENT_ID, DATFROM, DATTO);
    }
}
