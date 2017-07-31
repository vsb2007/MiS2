package bgroup.mysql.service;

import bgroup.mysql.model.PrintArchive;

import java.util.List;

/**
 * Created by VSB on 13.06.2017.
 * MiS2
 */
public interface PrintArchiveService {
    public List<PrintArchive> getPrintArchiveByPhone(String phoneNumber);
    public boolean savePrintArchiveToDb(PrintArchive printArchive);
}
