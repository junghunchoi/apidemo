package com.apidemo.config;


import javax.sql.DataSource;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

@Log4j2
@Configuration
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true) // 필요한 화면에만 보안설정을 할 수있는 어노테이션
public class CustomSecurityConfig {



    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // js ,css 같이 정적인 파일에는 필터링 하지않도록 조치
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        log.info("-----web configure-------");

        return (web -> web.ignoring()
            .requestMatchers(PathRequest.toStaticResources().atCommonLocations()));
    }

    // 설정을 통해 사용자가 접근을 제어한다.
    @Bean
    public SecurityFilterChain filterChain(final HttpSecurity http) throws Exception {
        log.info("-------------configure-----------");


        //csrf란 보안기술이 있는데 이를 처리하기 위한 과정이 복잡하므로 disable처리한다.
        http.csrf().disable();

        //세션을 사용하지 않는다는 설정
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        return http.build();
    }









}
