package bgroup.dao;

import bgroup.model.Amount;

/**
 * Created by VSB on 13.06.2017.
 * MiS2
 */
public interface AmountDao {
    public Amount getAmount(int PATIENT_ID, String dateStart, String dateStop);
}
