package bgroup.oracle.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Amount {
    static final Logger logger = LoggerFactory.getLogger(Amount.class);

    private String AMOUNT;
    private String AMOUNT2;

    public String getAMOUNT() {
        return AMOUNT;
    }

    public void setAMOUNT(String AMOUNT) {
        this.AMOUNT = AMOUNT;
    }

    public String getAMOUNT2() {
        return AMOUNT2;
    }

    public void setAMOUNT2(String AMOUNT2) {
        this.AMOUNT2 = AMOUNT2;
    }
}
