package bgroup.oracle.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;

public class HelpFio {
    static final Logger logger = LoggerFactory.getLogger(HelpFio.class);

    private String FIO;
    private String FIO2;
    private String FIO3;
    private String FIO4;
    private String FIO5;
    private String license;
    private BigDecimal POL;
    private String SNILS;
    private String SNILS1;
    private BigDecimal N;
    private BigDecimal N2;
    private BigDecimal PATNUM;
    private String GIVEDATE;
    private String GIVEDATE2;

    public String getFIO() {
        return FIO;
    }

    public void setFIO(String FIO) {
        this.FIO = FIO;
    }

    public String getFIO2() {
        return FIO2;
    }

    public void setFIO2(String FIO2) {
        this.FIO2 = FIO2;
    }

    public String getFIO3() {
        return FIO3;
    }

    public void setFIO3(String FIO3) {
        this.FIO3 = FIO3;
    }

    public String getFIO4() {
        return FIO4;
    }

    public void setFIO4(String FIO4) {
        this.FIO4 = FIO4;
    }

    public String getFIO5() {
        return FIO5;
    }

    public void setFIO5(String FIO5) {
        this.FIO5 = FIO5;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public BigDecimal getPOL() {
        return POL;
    }

    public void setPOL(BigDecimal POL) {
        this.POL = POL;
    }

    public String getSNILS() {
        return SNILS;
    }

    public void setSNILS(String SNILS) {
        this.SNILS = SNILS;
    }

    public String getSNILS1() {
        return SNILS1;
    }

    public void setSNILS1(String SNILS1) {
        this.SNILS1 = SNILS1;
    }

    public BigDecimal getN() {
        return N;
    }

    public void setN(BigDecimal n) {
        N = n;
    }

    public BigDecimal getN2() {
        return N2;
    }

    public void setN2(BigDecimal n2) {
        N2 = n2;
    }

    public BigDecimal getPATNUM() {
        return PATNUM;
    }

    public void setPATNUM(BigDecimal PATNUM) {
        this.PATNUM = PATNUM;
    }

    public String getGIVEDATE() {
        return GIVEDATE;
    }

    public void setGIVEDATE(String GIVEDATE) {
        this.GIVEDATE = GIVEDATE;
    }

    public String getGIVEDATE2() {
        return GIVEDATE2;
    }

    public void setGIVEDATE2(String GIVEDATE2) {
        this.GIVEDATE2 = GIVEDATE2;
    }
}
