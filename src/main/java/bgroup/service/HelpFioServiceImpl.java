package bgroup.service;

import bgroup.oracle.dao.HelpFioDaoImpl;
import bgroup.oracle.model.HelpFio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by VSB on 13.06.2017.
 * MiS2
 */
@Service("helpFioService")
@Transactional("transactionManager")
public class HelpFioServiceImpl implements HelpFioService {
    static final Logger logger = LoggerFactory.getLogger(HelpFioServiceImpl.class);
    @Autowired
    private HelpFioDaoImpl helpFioDao;

    @Override
    public HelpFio getHelpFio(int patiendId) {
        return helpFioDao.getHelpFio(patiendId);
    }
}
