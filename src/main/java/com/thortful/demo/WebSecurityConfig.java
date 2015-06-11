package com.thortful.demo;

import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.web.util.AntPathRequestMatcher;

@Configuration
@EnableWebMvcSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true, proxyTargetClass = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter
{

    @Configuration
    @Order(1)
    public static class WebUiSecurityConfig extends WebSecurityConfigurerAdapter
    {
        public void configure(WebSecurity web) throws Exception
        {
            web.ignoring().antMatchers("/css/**", "/js/**");
        }

        protected void configure(HttpSecurity httpSec) throws Exception
        {
            httpSec
                .csrf()
                    .disable()
                .authorizeRequests()
                    .antMatchers("/ui/","/ui/**","/app.js").hasAnyRole("USER")
                    .and()
                .formLogin()
                    .loginPage("/ui/login")
                    .defaultSuccessUrl("/ui", true)
                    .permitAll()
                    .and()
                .logout()
                    .logoutRequestMatcher(new AntPathRequestMatcher("/ui/logout"))
                    .logoutSuccessUrl("/ui/login")
                    .permitAll();
        }
    }

    @Configuration
    @Order(2)
    public static class ApiWebSecurityConfig extends WebSecurityConfigurerAdapter
    {
        protected void configure(HttpSecurity http) throws Exception
        {
            http.csrf()
                .disable()
            .authorizeRequests()
                .antMatchers("/api/**", "/stocks").hasAnyRole("API_USER", "USER")
                .antMatchers("/stocks/**").hasAnyRole("API_USER")
                .and()
            .httpBasic();
        }
    }


    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception
    {
        auth
            .inMemoryAuthentication()
                .withUser("user").password("password").roles("USER")
                .and()
                .withUser("apiuser").password("passtoken").roles("API_USER");
    }
}
