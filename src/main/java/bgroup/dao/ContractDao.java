package bgroup.dao;

import bgroup.model.Contract;

/**
 * Created by VSB on 13.06.2017.
 * MiS2
 */
public interface ContractDao {
    public Contract getContract(int PATIENT_ID);
}
