package bgroup.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by VSB on 21.06.2017.
 * MiS2
 */
public class ServDate {
    static final Logger logger = LoggerFactory.getLogger(ServDate.class);

    private String servdate;
    private String servdate2;
    private String minservdate;
    private String minservdate2;
    private String ONEDATE;
    private String ONEDATE2;

    public String getServdate() {
        return servdate;
    }

    public void setServdate(String servdate) {
        this.servdate = servdate;
    }

    public String getServdate2() {
        return servdate2;
    }

    public void setServdate2(String servdate2) {
        this.servdate2 = servdate2;
    }

    public String getMinservdate() {
        return minservdate;
    }

    public void setMinservdate(String minservdate) {
        this.minservdate = minservdate;
    }

    public String getMinservdate2() {
        return minservdate2;
    }

    public void setMinservdate2(String minservdate2) {
        this.minservdate2 = minservdate2;
    }

    public String getONEDATE() {
        return ONEDATE;
    }

    public void setONEDATE(String ONEDATE) {
        this.ONEDATE = ONEDATE;
    }

    public String getONEDATE2() {
        return ONEDATE2;
    }

    public void setONEDATE2(String ONEDATE2) {
        this.ONEDATE2 = ONEDATE2;
    }
}
