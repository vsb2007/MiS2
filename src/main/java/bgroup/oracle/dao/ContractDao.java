package bgroup.oracle.dao;

import bgroup.oracle.model.Contract;

/**
 * Created by VSB on 13.06.2017.
 * MiS2
 */
public interface ContractDao {
    public Contract getContract(int PATIENT_ID);
}
