package com.ldh.config;

import com.ldh.modules.authority.service.impl.SysUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;
import java.util.UUID;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)//访问接口需要权限
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private SysUserDetailsService sysUserDetailsService;
    private final String DEFAULT_REMEMBER_ME_KEY= UUID.randomUUID().toString();
    @Value("${myConfig.security.tokenValiditySeconds}")
    private int tokenValiditySeconds;

    @Autowired
    private DataSource dataSource;//datasource 用的是springboot默认的application.yml中的配置

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/login").authenticated()
                .anyRequest().permitAll()
                .and()
                .rememberMe()
                .tokenRepository(persistentTokenRepository())
                .userDetailsService(sysUserDetailsService)
                .tokenValiditySeconds(tokenValiditySeconds)
                .and()
                .cors()
                .and()
                .csrf().disable()//关闭csrf 功能，登录失败存在的原因
                .formLogin();

    }


    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
        //自动创建相关的token表(首次运行时需要打开，二次运行时需要注解掉)
        //jdbcTokenRepository.setCreateTableOnStartup(true);
        return jdbcTokenRepository;
    }


}
