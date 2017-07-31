package bgroup.mysql.model;

import bgroup.oracle.model.CustomUser;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by VSB on 26.07.2017.
 * MiS2
 */
@Entity
@Table(name = "archive")
public class PrintArchive {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "phoneNumber")
    private String phoneNumber;
    @Column(name = "f")
    private String f;
    @Column(name = "i")
    private String i;
    @Column(name = "o")
    private String o;
    @Column(name = "text")
    @Type(type = "text")
    private String text;
    @Column(name = "datePrint")
    private Date datePrint;

    public PrintArchive(CustomUser user, String responseBody) {
        this.f = user.getLastName();
        this.i = user.getFirstName();
        this.o = user.getSecondName();
        this.phoneNumber = user.getCellular();
        this.datePrint = new Date();
        this.text = responseBody;
    }

    public Date getDatePrint() {
        return datePrint;
    }

    public void setDatePrint(Date datePrint) {
        this.datePrint = datePrint;
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

    public String getF() {
        return f;
    }

    public void setF(String f) {
        this.f = f;
    }

    public String getI() {
        return i;
    }

    public void setI(String i) {
        this.i = i;
    }

    public String getO() {
        return o;
    }

    public void setO(String o) {
        this.o = o;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
