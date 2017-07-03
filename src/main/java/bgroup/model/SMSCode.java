package bgroup.model;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * Created by VSB on 03.07.2017.
 * MiS2
 */
public class SMSCode {
    static final Logger logger = LoggerFactory.getLogger(SMSCode.class);

    private String phoneNumber;
    private String code;
    private boolean isDisabled;
    private boolean isCheckOut;
    private DateTime dateChecked;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
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

    public DateTime getDateChecked() {
        return dateChecked;
    }

    public void setDateChecked(DateTime dateChecked) {
        this.dateChecked = dateChecked;
    }
}
