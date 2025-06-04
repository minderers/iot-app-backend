package top.dl.security;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.DefaultAuthenticationEventPublisher;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import top.dl.framework.security.mobile.MobileAuthenticationProvider;
import top.dl.framework.security.mobile.MobileUserDetailsService;
import top.dl.framework.security.mobile.MobileVerifyCodeService;
import java.util.ArrayList;
import java.util.List;
/**
 * @author: minder
 * @createTime: 2025/04/27 15:21
 * @description:
 **/
@Configuration
@AllArgsConstructor
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final MobileUserDetailsService mobileUserDetailsService;
    private final MobileVerifyCodeService mobileVerifyCodeService;
    private final ApplicationEventPublisher applicationEventPublisher;
    @Bean
    DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        return daoAuthenticationProvider;
    }
    @Bean
    MobileAuthenticationProvider mobileAuthenticationProvider() {
        return new MobileAuthenticationProvider(mobileUserDetailsService,mobileVerifyCodeService);
    }
    @Bean
    public AuthenticationManager authenticationManager() {
        List<AuthenticationProvider> providerList = new ArrayList<>();
        providerList.add(daoAuthenticationProvider());
        providerList.add(mobileAuthenticationProvider());
        ProviderManager providerManager = new ProviderManager(providerList
        );
        providerManager.setAuthenticationEventPublisher(new DefaultAuthenticationEventPublisher(applicationEventPublisher));
        return providerManager;
    }
}
