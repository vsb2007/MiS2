package bgroup.mysql.model;



import org.hibernate.annotations.Type;

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
    private int code;
    @Type(type = "org.hibernate.type.NumericBooleanType")
    @Column(name = "isDisabled", columnDefinition = "BIT default 0")
    private boolean isDisabled;
    @Type(type = "org.hibernate.type.NumericBooleanType")
    @Column(name = "isCheckOut", columnDefinition = "BIT default 0")
    private boolean isCheckOut;
    //@Temporal(TemporalType.TIMESTAMP)
    //private DateTime dateChecked;

    public SmsCode(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        this.code = (int)(Math.random()*1000);
        this.isDisabled = false;
        this.isCheckOut = false;
        //this.dateChecked = new DateTime();
    }

    public SmsCode() {
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
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

   /*
   public DateTime getDateChecked() {
        return dateChecked;
    }

    public void setDateChecked(DateTime dateChecked) {
        this.dateChecked = dateChecked;
    }
    */
}
