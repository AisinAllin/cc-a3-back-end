package com.cc.cca3.config;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/postmusicalinstrument",
                        "/fetchuserinfo",
                        "/updateaddstatusfalse",
                        "/totalprice",
                        "/updateaddstatus",
                        "/updatereqnum",
                        "/removecartitem",
                        "/updateuerinfo",
                        "/getmusicinsbytype",
                        "/saveuserinfo",
                        "/getmusicalinstrument",
                        "/getcartinfo",
                        "/postcartinfo",
                        "/getuserinfo"
                )
                .permitAll()
                .antMatchers("/", "index", "/css/*", "/actuator/*")
                .permitAll()
                .anyRequest()
                .authenticated();
    }

    @Override
    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }
}

