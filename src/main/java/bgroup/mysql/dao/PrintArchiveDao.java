package bgroup.mysql.dao;

import bgroup.mysql.model.PrintArchive;
import bgroup.mysql.model.SmsCode;

import java.util.List;

/**
 * Created by VSB on 13.06.2017.
 * MiS2
 */
public interface PrintArchiveDao {
    public List<PrintArchive> getPrintArchiveListByPhone(String phoneNumber);
    public boolean savePrintArchiveToDB(PrintArchive printArchive);
}
