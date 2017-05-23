package bgroup.model;

/**
 * Created by VSB on 15.05.2017.
 * MiS3
 */

import java.util.List;

import bgroup.configuration.AppConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;

public class Role implements GrantedAuthority {
    static final Logger logger = LoggerFactory.getLogger(Role.class);

    private String name;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthority() {
        return this.name;
    }
}
