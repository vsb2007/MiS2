package bgroup.authentication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by VSB on 16.05.2017.
 * MiS3
 */

//@Component
public class CustomUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    static final Logger logger = LoggerFactory.getLogger(CustomUsernamePasswordAuthenticationFilter.class);

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        final String lastName = request.getParameter("lastName");
        final String firstName = request.getParameter("firstName");
        final String secondName = request.getParameter("secondName");
        final String birthDate = request.getParameter("birthDate");
        final String phone = request.getParameter("phone");
        request.getSession().setAttribute("lastName", lastName);
        request.getSession().setAttribute("firstName", firstName);
        request.getSession().setAttribute("secondName", secondName);
        request.getSession().setAttribute("birthDate", birthDate);
        request.getSession().setAttribute("phone", phone);

        logger.debug("lastName:" + request.getParameter("lastName"));
        logger.debug("username:" + request.getParameter("username"));
        Authentication authentication = super.attemptAuthentication(request, response);

        return authentication;
    }

    private String lastName = "lastName";
    private String firstName = "firstName";
    private String secondName = "secondName";
    private String birthDate = "birthDate";
    private String phone = "phone";
    private String delimiter = "|";

    @Override
    protected String obtainUsername(HttpServletRequest request) {
        String username = request.getParameter(getUsernameParameter()).replaceAll("\\|","");
        String lastname = request.getParameter(getLastName()).replaceAll("\\|","");
        String firstname = request.getParameter(getFirstName()).replaceAll("\\|","");
        String secondname = request.getParameter(getSecondName()).replaceAll("\\|","");
        String birthdate = request.getParameter(getBirthDate()).replaceAll("\\|","");
        String phone = request.getParameter(getPhone()).replaceAll("\\|","");

        String combinedUsername = username
                + getDelimiter() + lastname
                + getDelimiter() + firstname
                + getDelimiter() + secondname
                + getDelimiter() + birthdate
                + getDelimiter() + phone;

        logger.debug("Combined username = " + combinedUsername);
        return combinedUsername;
    }

    /**
     * @return The parameter name which will be used to obtain the extra input
     * from the login request
     */
    public String getLastName() {
        return this.lastName;
    }

    /**
     * @param lastName The parameter name which will be used to obtain the extra
     *                 input from the login request
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return The delimiter string used to separate the username and extra
     * input values in the string returned by
     * <code>obtainUsername()</code>
     */
    public String getDelimiter() {
        return this.delimiter;
    }

    /**
     * @param delimiter The delimiter string used to separate the username and extra
     *                  input values in the string returned by
     *                  <code>obtainUsername()</code>
     */
    public void setDelimiter(String delimiter) {
        this.delimiter = delimiter;
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

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
