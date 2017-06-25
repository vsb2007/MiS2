package bgroup.service;

import bgroup.dao.ContractDaoImpl;
import bgroup.dao.HelpFioDaoImpl;
import bgroup.model.Contract;
import bgroup.model.HelpFio;
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
@Transactional
public class HelpFioServiceImpl implements HelpFioService {
    static final Logger logger = LoggerFactory.getLogger(HelpFioServiceImpl.class);
    @Autowired
    private HelpFioDaoImpl helpFioDao;

    @Override
    public HelpFio getHelpFio(int patiendId) {
        return helpFioDao.getHelpFio(patiendId);
    }
}
