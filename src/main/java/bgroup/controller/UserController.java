package bgroup.controller;


import bgroup.model.CustomUser;
import bgroup.service.CustomUserService;
import bgroup.service.CustomUserServiceImpl;
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
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;


@Controller
public class UserController {
    static final Logger logger = LoggerFactory.getLogger(UserController.class);

    // @Autowired
    //CustomUserService userService;


    /* @Autowired
     UserProfileService userProfileService;
 */
    @Autowired
    MessageSource messageSource;

    /*@Autowired
    PersistentTokenBasedRememberMeServices persistentTokenBasedRememberMeServices;
    */
    //@Autowired
    //AuthenticationTrustResolver authenticationTrustResolver;

    /**
     * This method will list all existing users.
     */
    @RequestMapping(value = {"/", "index"}, method = RequestMethod.GET)
    public String indexPage(Model model) {
        /*Map<String,String> param = new HashMap<String, String>();
        param.put("error","error");
        model.addAttribute("param",param);
        return "login";*/
        return "index";
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
    public ModelAndView accessDeniedPage() {
        ModelAndView model = new ModelAndView();
        model.addObject("error", "Ошибка авторизации");
        logger.debug("Ошибка авторизации");
        model.setViewName("login");
        return model;
        //return "accessDenied";
    }

    /**
     * This method handles login GET requests.
     * If users is already logged-in and tries to goto login page again, will be redirected to list page.
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage(Model model) {
        Object principal = null;
        try {
            principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        } catch (BadCredentialsException ex) {

        }
        //Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        CustomUser user = null;
        if (principal != null && principal instanceof CustomUser) {
            user = ((CustomUser) principal);
        }
        if (user != null)
            logger.debug("Авторизован:" + user.getUsername());
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
        return "redirect:/login?logout";
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