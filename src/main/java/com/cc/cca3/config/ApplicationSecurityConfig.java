package com.cc.cca3.config;


import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
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

    private static final String REGION = "ap-southeast-2";
    private static final String ACCESS_KEY = "";
    private static final String SECRET_KEY = "";

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/postmusicalinstrument",
                        "/fetchuserinfo",
                        "/updateaddstatusfalse",
                        "/totalprice",
                        "/s3imglink",
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

    @Bean
    public AmazonS3 s3() {
        AWSCredentials awsCredentials = new BasicAWSCredentials(ACCESS_KEY, SECRET_KEY);

        return AmazonS3ClientBuilder
                .standard()
                .withRegion("ap-southeast-2")
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .build();
    }
}

