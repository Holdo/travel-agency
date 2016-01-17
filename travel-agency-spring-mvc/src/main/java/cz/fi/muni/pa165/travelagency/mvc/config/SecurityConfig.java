package cz.fi.muni.pa165.travelagency.mvc.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

/**
 *
 * @author Michal Holic
 */
@Configuration
@ImportResource("classpath:/SpringXMLConfig.xml")
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource).passwordEncoder(passwordEncoder())
                .usersByUsernameQuery(
                        "select username, password, enabled from Administrator where username=?")
                .authoritiesByUsernameQuery(
                        "select username, role from Administrator where username=?");
        auth.jdbcAuthentication().dataSource(dataSource).passwordEncoder(passwordEncoder())
                .usersByUsernameQuery(
                        "select username, password, enabled from Customer where username=?")
                .authoritiesByUsernameQuery(
                        "select username, role from Customer where username=?");
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/customer/list**").access("hasRole('ROLE_ADMIN')")
                .antMatchers("/reservation/list**").access("hasRole('ROLE_ADMIN')")
                .antMatchers("/excursion/list").access("hasRole('ROLE_ADMIN')")
                //.anyRequest().permitAll()
                .and()
            .formLogin()
                .loginPage("/login")
                .failureUrl("/login?error")
                .usernameParameter("username").passwordParameter("password")
                .permitAll()
                .and()
            .logout()
                .logoutSuccessUrl("/login?logout")
                .permitAll()
                .and()
            .exceptionHandling()
                .accessDeniedPage("/403")
                .and()
            .csrf();
    }

    @Bean(name="passwordEncoder")
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(4);
    }

}
