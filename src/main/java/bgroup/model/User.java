package bgroup.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotEmpty;

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
    //private String userName;

    /*@Type(type = "org.hibernate.type.NumericBooleanType")
    @Column(name = "BLOCKED", columnDefinition = "BIT default 1")
    private Boolean blocked;

    @Type(type = "org.hibernate.type.NumericBooleanType")
    @Column(name = "DELETED", columnDefinition = "BIT default 0")
    private Boolean deleted;
*/
  /*  @NotEmpty
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "APP_USER_USER_PROFILE",
            joinColumns = {@JoinColumn(name = "USER_ID")},
            inverseJoinColumns = {@JoinColumn(name = "USER_PROFILE_ID")})
            private Set<UserProfile> userProfiles = new HashSet<UserProfile>();
            */



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
