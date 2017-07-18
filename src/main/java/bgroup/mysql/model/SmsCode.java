package bgroup.mysql.model;


import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotEmpty;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by VSB on 03.07.2017.
 * MiS2
 */
@Entity
@Table(name = "smscode")
public class SmsCode implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "phoneNumber")
    private String phoneNumber;
    @Column(name = "code")
    private Integer code;
    //@Type(type = "org.hibernate.type.TimestampType")
    //@Temporal(TemporalType.TIMESTAMP)

    @Column(name = "dateChecked")
    private Date dateChecked;
    @Type(type = "org.hibernate.type.NumericBooleanType")
    @Column(name = "isDisabled", columnDefinition = "INT default 0")
    private boolean isDisabled;
    @Type(type = "org.hibernate.type.NumericBooleanType")
    @Column(name = "isCheckOut", columnDefinition = "INT default 0")
    private boolean isCheckOut;

    public SmsCode(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        this.code = (int) (Math.random() * 1000);
        this.isDisabled = false;
        this.isCheckOut = false;
        this.dateChecked = null;
    }

    public SmsCode() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public boolean isDisabled() {
        return isDisabled;
    }

    public void setDisabled(boolean disabled) {
        isDisabled = disabled;
    }

    public boolean isCheckOut() {
        return isCheckOut;
    }

    public void setCheckOut(boolean checkOut) {
        isCheckOut = checkOut;
    }

    public Date getDateChecked() {
        return dateChecked;
    }

    public void setDateChecked(Date dateChecked) {
        this.dateChecked = dateChecked;
    }
}
