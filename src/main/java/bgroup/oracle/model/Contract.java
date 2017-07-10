package bgroup.oracle.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;

/**
 * Created by VSB on 08.06.2017.
 * MiS2
 */

public class Contract {

    private BigDecimal KEYID;

    private String CELLULAR;
    private String doverennost;
    private String doverennost2;
    private String doverennost3;
    private String doverennost4;
    private String license;
    private String license2;
    private String license3;
    private String license4;
    private String fio;
    private String fio2;
    private String fio3;
    private String fio4;
    private String fio5;
    private String fio6;
    private BigDecimal PATNUM;
    private BigDecimal PATNUM2;
    private String region1;
    private String address;
    private String address2;
    private String address3;
    private String address4;
    private String PHONE;
    private String PHONE2;
    private String SEX_NAME;
    private String REGISTRATOR;
    private String REGISTRATOR2;
    private String REGISTRATOR3;
    private String REGISTRATOR4;
    private String REGISTRATOR5;
    private String BIRTH_DATE;
    //     , P.*
    private String COMPANY;
    private String POLICE;
    private String PRIVILS;

    static final Logger logger = LoggerFactory.getLogger(Contract.class);

    public String getPRIVILS() {
        return PRIVILS;
    }

    public void setPRIVILS(String PRIVILS) {
        this.PRIVILS = PRIVILS;
    }

    public String getCELLULAR() {
        return CELLULAR;
    }

    public void setCELLULAR(String CELLULAR) {
        this.CELLULAR = CELLULAR;
    }

    public Contract() {
    }

    public BigDecimal getKEYID() {
        return KEYID;
    }

    public void setKEYID(BigDecimal keyId) {
        this.KEYID = keyId;
    }

    public String getDoverennost() {
        return doverennost;
    }

    public void setDoverennost(String doverennost) {
        this.doverennost = doverennost;
    }

    public String getDoverennost2() {
        return doverennost2;
    }

    public void setDoverennost2(String doverennost2) {
        this.doverennost2 = doverennost2;
    }

    public String getDoverennost3() {
        return doverennost3;
    }

    public void setDoverennost3(String doverennost3) {
        this.doverennost3 = doverennost3;
    }

    public String getDoverennost4() {
        return doverennost4;
    }

    public void setDoverennost4(String doverennost4) {
        this.doverennost4 = doverennost4;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public String getLicense2() {
        return license2;
    }

    public void setLicense2(String license2) {
        this.license2 = license2;
    }

    public String getLicense3() {
        return license3;
    }

    public void setLicense3(String license3) {
        this.license3 = license3;
    }

    public String getLicense4() {
        return license4;
    }

    public void setLicense4(String license4) {
        this.license4 = license4;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public String getFio2() {
        return fio2;
    }

    public void setFio2(String fio2) {
        this.fio2 = fio2;
    }

    public String getFio3() {
        return fio3;
    }

    public void setFio3(String fio3) {
        this.fio3 = fio3;
    }

    public String getFio4() {
        return fio4;
    }

    public void setFio4(String fio4) {
        this.fio4 = fio4;
    }

    public String getFio5() {
        return fio5;
    }

    public void setFio5(String fio5) {
        this.fio5 = fio5;
    }

    public String getFio6() {
        return fio6;
    }

    public void setFio6(String fio6) {
        this.fio6 = fio6;
    }

    public BigDecimal getPATNUM() {
        return PATNUM;
    }

    public void setPATNUM(BigDecimal patnum) {
        this.PATNUM = patnum;
    }

    public BigDecimal getPATNUM2() {
        return PATNUM2;
    }

    public void setPATNUM2(BigDecimal patnum2) {
        this.PATNUM2 = patnum2;
    }

    public String getRegion1() {
        return region1;
    }

    public void setRegion1(String region1) {
        this.region1 = region1;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getAddress3() {
        return address3;
    }

    public void setAddress3(String address3) {
        this.address3 = address3;
    }

    public String getAddress4() {
        return address4;
    }

    public void setAddress4(String address4) {
        this.address4 = address4;
    }

    public String getPHONE() {
        return PHONE;
    }

    public void setPHONE(String phone) {
        this.PHONE = phone;
    }

    public String getPHONE2() {
        return PHONE2;
    }

    public void setPHONE2(String phone2) {
        this.PHONE2 = phone2;
    }

    public String getSEX_NAME() {
        return SEX_NAME;
    }

    public void setSEX_NAME(String SEX_NAME) {
        this.SEX_NAME = SEX_NAME;
    }

    public String getREGISTRATOR() {
        return REGISTRATOR;
    }

    public void setREGISTRATOR(String registrator) {
        this.REGISTRATOR = registrator;
    }

    public String getREGISTRATOR2() {
        return REGISTRATOR2;
    }

    public void setREGISTRATOR2(String REGISTRATOR2) {
        this.REGISTRATOR2 = REGISTRATOR2;
    }

    public String getREGISTRATOR3() {
        return REGISTRATOR3;
    }

    public void setREGISTRATOR3(String REGISTRATOR3) {
        this.REGISTRATOR3 = REGISTRATOR3;
    }

    public String getREGISTRATOR4() {
        return REGISTRATOR4;
    }

    public void setREGISTRATOR4(String REGISTRATOR4) {
        this.REGISTRATOR4 = REGISTRATOR4;
    }

    public String getREGISTRATOR5() {
        return REGISTRATOR5;
    }

    public void setREGISTRATOR5(String REGISTRATOR5) {
        this.REGISTRATOR5 = REGISTRATOR5;
    }

    public String getBIRTH_DATE() {
        return BIRTH_DATE;
    }

    public void setBIRTH_DATE(String BIRTH_DATE) {
        this.BIRTH_DATE = BIRTH_DATE;
    }

    public String getCOMPANY() {
        return COMPANY;
    }

    public void setCOMPANY(String COMPANY) {
        this.COMPANY = COMPANY;
    }

    public String getPOLICE() {
        return POLICE;
    }

    public void setPOLICE(String POLICE) {
        this.POLICE = POLICE;
    }
}
