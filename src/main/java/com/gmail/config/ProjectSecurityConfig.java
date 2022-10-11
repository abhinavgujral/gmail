package com.gmail.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.bind.annotation.GetMapping;

@Configuration
public class ProjectSecurityConfig {

    @Bean
    public SecurityFilterChain gmailUserConfig(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.authorizeHttpRequests(auth ->{
            try {

					auth
						.antMatchers("/admin/**").hasRole("ADMIN")
						.antMatchers("/mail","/register","/login","/welcome").permitAll()
				        .antMatchers("/inbox","/sentBox","/recieved","/compose","/starred/**","/deleteMail","/starred/","/upload")
				        .authenticated()
				        .and().csrf().disable()
                        .logout()
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/end");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
            ).httpBasic(Customizer.withDefaults());

              

        return httpSecurity.build();

    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
