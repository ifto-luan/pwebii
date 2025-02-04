package com.pwebii.jpa_heranca.security;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import com.pwebii.jpa_heranca.controller.CustomLoginSuccessHandler;

@Configuration // classe de configuração
@EnableWebSecurity // indica ao Spring que serão definidas configurações personalizadas de
                   // segurança
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(
                customizer -> customizer
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers("/cart/finish").hasRole("USER")
                        .anyRequest()
                        .permitAll()
        )
                .formLogin(customizer -> customizer
                        .loginPage("/login") 
                        .successHandler(new CustomLoginSuccessHandler())
                        .permitAll()
                                     
                )
                .httpBasic(withDefaults())
                .logout(LogoutConfigurer::permitAll) 
                .rememberMe(withDefaults());
        return http.build();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails user1 = User.withUsername("user")
                .password(passwordEncoder().encode("123"))
                .roles("USER")
                .build();
        UserDetails admin = User.withUsername("admin")
                .password(passwordEncoder().encode("admin"))
                .roles("ADMIN")
                .build();
        return new InMemoryUserDetailsManager(user1, admin);
    }

    /**
     * Com o método, instanciamos uma instância do encoder BCrypt e deixando o
     * controle dessa instância como responsabilidade do Spring.
     * Agora, sempre que o Spring Security necessitar condificar um senha, ele já
     * terá o que precisa configurado.
     * 
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}