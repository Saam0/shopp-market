package com.shopmarket.configurations;

import com.shopmarket.security.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfiguration extends WebSecurityConfigurerAdapter {

//    @Autowired
//    DataSource dataSource;
    @Autowired
    private MyUserDetailsService userDetailsService;

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//
//        auth.jdbcAuthentication().dataSource(dataSource).passwordEncoder(superPAss())
//                .usersByUsernameQuery(
//                        "Select email,password,status FROM user_tab WHERE email = ?")
//                .authoritiesByUsernameQuery(
//                        "Select email from user_tab where email=?");
//
//    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()

//                .antMatchers("/reg","/block").anonymous()
//                .antMatchers("/admin/**").hasRole("ADMIN")
//                .antMatchers("/user/new").anonymous()
//                .antMatchers("/**").anonymous()
                .antMatchers("/**").permitAll()
//                .antMatchers("/user/new1").anonymous()
//                .antMatchers("/customer/new").anonymous()
//                .antMatchers("/customer/_new1").anonymous()
//                .antMatchers("/customer/modal2").anonymous()
//                .antMatchers("/customer/modal1").anonymous()
//                .antMatchers("/customer/index").anonymous()
//                .antMatchers("/index").permitAll()
//                .antMatchers("/add-user","/reset/**").permitAll()

//                .antMatchers("/login/**").anonymous()

//                .antMatchers("/admin/**").hasAnyRole("admin")

                .anyRequest().authenticated()

                .and()

                .formLogin()
                .loginPage("/login").permitAll()
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//                .logoutUrl("/logout")
                .logoutSuccessUrl("/");
//                .permitAll().logoutSuccessUrl("/");
    }


    @Bean
    public PasswordEncoder superPAss() {
        return new BCryptPasswordEncoder();
    }
}
