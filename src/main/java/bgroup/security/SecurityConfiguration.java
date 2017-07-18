package bgroup.security;

import bgroup.authentication.CustomUsernamePasswordAuthenticationFilter;
import bgroup.authentication.CustomAuthenticationProvider;
import bgroup.service.CustomUserService;
import bgroup.service.CustomUserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.filter.CharacterEncodingFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    static final Logger logger = LoggerFactory.getLogger(SecurityConfiguration.class);

    @Autowired
    private CustomUserService userService;

    /*
    @Autowired
    PersistentTokenRepository tokenRepository;
*/
    @Autowired
    private CustomAuthenticationProvider customAuthenticationProvider;

    @Autowired
    private CustomUsernamePasswordAuthenticationFilter customUsernamePasswordAuthenticationFilter; // = new CustomUsernamePasswordAuthenticationFilter();

    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(customAuthenticationProvider);
        //auth.userDetailsService(userService);
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /*
        Включаем правильную кодировку для spring security
        */
        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding("UTF-8");
        filter.setForceEncoding(true);
        http.addFilterBefore(filter, CsrfFilter.class);
        //http.addFilter(authFilter);


        http
                .authorizeRequests()
                .antMatchers("/css/**").permitAll()
                .antMatchers("/images/**").permitAll()
                .antMatchers("/js/**").permitAll()
                .antMatchers("/demo-files/**").permitAll()
                .antMatchers("/fonts/**").permitAll()
                //.antMatchers("/secured/*").access("hasRole('LOGIN')")
                //.antMatchers("/**/").access("hasRole('LOGIN')")

                /*
                .antMatchers("/secured/*").access("hasRole('ROLE_USER')")
                .antMatchers("/*").permitAll()
                .and()
                .formLogin().loginPage("/login").permitAll()
                .loginProcessingUrl("/login").usernameParameter("userName").passwordParameter("password").and()
                //.rememberMe().rememberMeParameter("remember-me").tokenRepository(tokenRepository)
                //.tokenValiditySeconds(86400)
                //.and()
                .csrf()
                .and()
                .exceptionHandling().accessDeniedPage("/Access_Denied")
                */
                .antMatchers("/page1").access("hasRole('ROLE_USER_PRE')")
                .antMatchers("/checkCode").access("hasRole('ROLE_USER_PRE')")
                .antMatchers("/").access("hasRole('ROLE_USER_PRE')")
                .antMatchers("/logout").permitAll()
                //.antMatchers("/").permitAll()
                .antMatchers("/**/").access("hasRole('ROLE_USER')")
                .and()
                .formLogin().failureUrl("/Access_Denied").loginPage("/login").permitAll().loginProcessingUrl("/login")
                .and()
                .exceptionHandling().accessDeniedPage("/Access_Denied")
                .and()
                //.rememberMe().rememberMeParameter("remember-me").tokenRepository(tokenRepository)
                //.tokenValiditySeconds(86400)
                //.and()
                .csrf()



        ;

        //http.addFilterBefore(customUsernamePasswordAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        http.addFilter(customUsernamePasswordAuthenticationFilter);

    }

    @Bean
    public CustomUsernamePasswordAuthenticationFilter customUsernamePasswordAuthenticationFilter()
            throws Exception {
        CustomUsernamePasswordAuthenticationFilter customUsernamePasswordAuthenticationFilter = new CustomUsernamePasswordAuthenticationFilter();
        customUsernamePasswordAuthenticationFilter
                .setAuthenticationManager(authenticationManagerBean());
        return customUsernamePasswordAuthenticationFilter;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    /*
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
*/


    /* @Bean
     public PersistentTokenBasedRememberMeServices getPersistentTokenBasedRememberMeServices() {
         PersistentTokenBasedRememberMeServices tokenBasedservice = new PersistentTokenBasedRememberMeServices(
                 "remember-me", userDetailsService, tokenRepository);
         return tokenBasedservice;
     }
 */
    @Bean
    public AuthenticationTrustResolver getAuthenticationTrustResolver() {
        return new AuthenticationTrustResolverImpl();
    }

}
