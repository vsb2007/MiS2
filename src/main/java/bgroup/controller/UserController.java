package bgroup.controller;


import bgroup.oracle.model.CustomUser;
import bgroup.oracle.model.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Calendar;
import java.util.Date;


@Controller
public class UserController {
    static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    MessageSource messageSource;

    @RequestMapping(value = {"/", "index"}, method = RequestMethod.GET)
    public ModelAndView indexPage() {
        /*Map<String,String> param = new HashMap<String, String>();
        param.put("error","error");
        model.addAttribute("param",param);
        return "login";*/
        ModelAndView model = new ModelAndView();
        CustomUser user = getCustomerUser();
        model.addObject("user", user);
        if (user != null) {
            if (user.isUserHasRole("ROLE_USER")) {
                //model.setViewName("page2");
                return innPage();
            }else
            if (user.isUserHasRole("ROLE_USER_PRE")) {
                model.setViewName("page1");
                return model;
            }
        }
        model.setViewName("index");
        return model;
    }


/*
    @RequestMapping(value = {"userslist"}, method = RequestMethod.GET)
    public String listUsers(ModelMap model) {
        List<User> users = userService.findAllUsers();
        model.addAttribute("users", users);
        model.addAttribute("loggedinuser", getPrincipal());
        return "userslist";
    }
*/

    /**
     * This method handles Access-Denied redirect.
     */
    @RequestMapping(value = "/Access_Denied")
    public ModelAndView accessDeniedPage(HttpServletRequest request) {
        ModelAndView model = new ModelAndView();
        model.addObject("error", "Ошибка авторизации!!!<br>Или Вам необходимо обратиться в регистратуру и пройти регистрацию в базе");
        model.addObject("lastName", request.getParameter("lastName"));
        model.addObject("firstName", request.getParameter("firstName"));
        model.addObject("secondName", request.getParameter("secondName"));
        model.addObject("birthDate", request.getParameter("birthDate"));
        model.addObject("phone", request.getParameter("phone"));
        model.setViewName("login");
        return model;
        //return "accessDenied";
    }

    @RequestMapping(value = "/page2")
    public ModelAndView innPage() {
        ModelAndView model = new ModelAndView();
        //model.addObject("error", "Ошибка авторизации");
        CustomUser user = getCustomerUser();
        model.setViewName("page2");
        model.addObject("user", user);
        model.addObject("years", getYears());
        return model;
    }

    private int[] getYears() {
        int[] years = new int[3];
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int thisYear = cal.get(Calendar.YEAR);
        years[0] = thisYear - 1;
        years[1] = thisYear - 2;
        years[2] = thisYear - 3;
        //logger.debug(years[0] + " " + years[1] + years[2]);
        return years;
    }

    private CustomUser getCustomerUser() {
        Object principal = null;
        try {
            principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        } catch (BadCredentialsException ex) {

        }
        CustomUser user = null;
        if (principal != null && principal instanceof CustomUser) {
            user = ((CustomUser) principal);
        }
        return user;
    }

    /**
     * This method handles login GET requests.
     * If users is already logged-in and tries to goto login page again, will be redirected to list page.
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage(Model model) {
        Object principal = null;
        CustomUser user = getCustomerUser();
        if (user != null)
            logger.debug("Авторизован:" + user.getLastName());
        else logger.debug("Не авторизован");
        model.addAttribute("user", user);
        return "login";
    }

    /**
     * This method handles logout requests.
     * Toggle the handlers if you are RememberMe functionality is useless in your app.
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
            //persistentTokenBasedRememberMeServices.logout(request, response, auth);
            SecurityContextHolder.getContext().setAuthentication(null);
        }
        return "redirect:/login";
    }

    /**
     * This method returns the principal[user-name] of logged-in user.
     */
    private String getPrincipal() {
        String userName = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal == null) return null;
        if (principal instanceof UserDetails) {
            userName = ((UserDetails) principal).getUsername();
        } else {
            userName = principal.toString();
        }
        return userName;
    }

    /**
     * This method returns true if users is already authenticated [logged-in], else false.
     */
    /*
    private boolean isCurrentAuthenticationAnonymous() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authenticationTrustResolver.isAnonymous(authentication);
    }
*/

}