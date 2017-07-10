package bgroup.service;

import bgroup.oracle.model.Amount;
import bgroup.oracle.model.Contract;

/**
 * Created by VSB on 13.06.2017.
 * MiS2
 */
public interface AmountService {
    public Amount getAmount(int PATIENT_ID, String DATFROM, String DATTO);
}
