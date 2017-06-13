package bgroup.model;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by VSB on 08.06.2017.
 * MiS2
 */
public class Contract {
    @Autowired
    private SessionFactory sessionFactory;

    Integer keyID;
    String cellular;
    String doverennost;
    String doverennost2;
    String doverennost3;
    String doverennost4;
    String license;
    String license2;
    String license3;
    String license4;
    String fio;
    String fio2;
    String fio3;
    String fio4;
    String fio5;
    String fio6;
    String patnum;
    String patnum2;
    String region1;
    String address;
    String address2;
    String address3;
    String address4;
    String phone;
    String phone2;
    String sex_name;
    String registrator;
    String registrator2;
    String registrator3;
    String registrator4;
    String registrator5;
    String birth_date;
    //     , P.*
    String company;
    String police;
    String privils;

    static final Logger logger = LoggerFactory.getLogger(Contract.class);

    public String getPrivils() {
        return privils;
    }

    public void setPrivils(String privils) {
        this.privils = privils;
    }

    public String getCellular() {
        return cellular;
    }

    public void setCellular(String cellular) {
        this.cellular = cellular;
    }

    public Contract() {
    }

    public Integer getKeyID() {
        return keyID;
    }

    public void setKeyID(Integer keyID) {
        this.keyID = keyID;
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

    public String getPatnum() {
        return patnum;
    }

    public void setPatnum(String patnum) {
        this.patnum = patnum;
    }

    public String getPatnum2() {
        return patnum2;
    }

    public void setPatnum2(String patnum2) {
        this.patnum2 = patnum2;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhone2() {
        return phone2;
    }

    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }

    public String getSex_name() {
        return sex_name;
    }

    public void setSex_name(String sex_name) {
        this.sex_name = sex_name;
    }

    public String getRegistrator() {
        return registrator;
    }

    public void setRegistrator(String registrator) {
        this.registrator = registrator;
    }

    public String getRegistrator2() {
        return registrator2;
    }

    public void setRegistrator2(String registrator2) {
        this.registrator2 = registrator2;
    }

    public String getRegistrator3() {
        return registrator3;
    }

    public void setRegistrator3(String registrator3) {
        this.registrator3 = registrator3;
    }

    public String getRegistrator4() {
        return registrator4;
    }

    public void setRegistrator4(String registrator4) {
        this.registrator4 = registrator4;
    }

    public String getRegistrator5() {
        return registrator5;
    }

    public void setRegistrator5(String registrator5) {
        this.registrator5 = registrator5;
    }

    public String getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(String birth_date) {
        this.birth_date = birth_date;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getPolice() {
        return police;
    }

    public void setPolice(String police) {
        this.police = police;
    }
}
