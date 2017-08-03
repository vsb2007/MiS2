package bgroup.oracle.model;

import java.io.Serializable;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "patient")
public class User implements Serializable {

    /*@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
*/
    @Id
    @Column(name = "KEYID")
    private Integer keyId;

    @Column(name = "LASTNAME", nullable = true)
    private String lastName;

    @Column(name = "FIRSTNAME", nullable = true)
    private String firstName;

    @Column(name = "SECONDNAME", nullable = true)
    private String secondName;

    @Column(name = "CELLULAR", nullable = true)
    private String cellular;

    @Column(name = "PHONE", nullable = true)
    private String phone;

    @Column(name = "BIRTHDATE", nullable = true)
    @Type(type = "date")
    private Date birthDate;

    @Column(name = "SEX")
    private Integer sex;

    public Integer getKeyId() {
        return keyId;
    }

    public void setKeyId(Integer keyId) {
        this.keyId = keyId;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getCellular() {
        return cellular;
    }

    public void setCellular(String cellular) {
        this.cellular = cellular;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    /*
       * DO-NOT-INCLUDE passwords in toString function.
       * It is done here just for convenience purpose.
       */
    @Override
    public String toString() {
        return "User [keyId=" + keyId + ", lastName=" + lastName
                + ", firstName=" + firstName + ", secondName=" + secondName
                + ", birthDay=" + birthDate + "]";
    }
}
