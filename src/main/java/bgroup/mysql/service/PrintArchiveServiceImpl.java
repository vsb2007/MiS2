package bgroup.mysql.service;

import bgroup.mysql.dao.PrintArchiveDaoImpl;
import bgroup.mysql.model.PrintArchive;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by VSB on 13.06.2017.
 * MiS2
 */
@Service("printArchiveService")
@Transactional("transactionManagerMysql")
public class PrintArchiveServiceImpl implements PrintArchiveService {
    static final Logger logger = LoggerFactory.getLogger(PrintArchiveServiceImpl.class);
    @Autowired
    private PrintArchiveDaoImpl printArchiveDao;

    @Override
    public List<PrintArchive> getPrintArchiveByPhone(String phoneNumber) {
        return printArchiveDao.getPrintArchiveListByPhone(phoneNumber);
    }

    @Override
    public boolean savePrintArchiveToDb(PrintArchive printArchive) {
        try {
            if (!printArchiveDao.savePrintArchiveToDB(printArchive))
                return false;
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
