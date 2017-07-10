package bgroup.service;

import bgroup.oracle.model.Contract;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bgroup.oracle.dao.ContractDaoImpl;

/**
 * Created by VSB on 13.06.2017.
 * MiS2
 */
@Service("contractService")
@Transactional("transactionManager")
public class ContractServiceImpl implements ContractService {
    static final Logger logger = LoggerFactory.getLogger(ContractServiceImpl.class);
    @Autowired
    private ContractDaoImpl contractDao;

    @Override
    public Contract getDog(int patiendId) {
        logger.debug("ContractServiceImpl:" + patiendId);
        return contractDao.getContract(patiendId);
    }
}
