package bgroup.service;

import bgroup.model.Contract;
import bgroup.model.ServDate;

/**
 * Created by VSB on 13.06.2017.
 * MiS2
 */
public interface ServDateService {
    public ServDate getServDate(int PATIENT_ID, String DATFROM, String DATTO);
}
