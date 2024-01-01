package com.example.productService.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtDecoders;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConifg{
        /**
         * Authenticate every request
         * @param http
         * @return
         * @throws Exception
         */


        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {


                http
                        .authorizeHttpRequests((authorize) -> authorize
                            .requestMatchers("/products/**").permitAll()
                                .requestMatchers("/search").permitAll()
                            .anyRequest().authenticated()
                    )
                        .oauth2ResourceServer((oauth2) -> oauth2
                                .jwt(Customizer.withDefaults())
                        ).cors().disable().csrf().disable();
                return http.build();
        }

        @Bean
        public JwtDecoder jwtDecoder() {
                return JwtDecoders.fromIssuerLocation("http://localhost:9000");
//        @Bean
//        public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http)
//                throws Exception {
//
//
//
//        http.authorizeHttpRequests((authorize) -> authorize.anyRequest().authenticated())
//               .formLogin(Customizer.withDefaults())
//                .cors().disable()
//                .csrf().disable();
//
////
////        http.authorizeHttpRequests((authorize)->authorize
////              //  authorize.requestMatchers("/products").hasAuthority("admin")
////                        .requestMatchers("/*").permitAll());
//             //   .formLogin(Customizer.withDefaults());
////                            .anyRequest().authenticated()
////         http
////                        .authorizeHttpRequests((authorize) -> authorize
////                                .requestMatchers("/*").permitAll()
////
////                        )
////                        .oauth2ResourceServer((oauth2) -> oauth2.jwt(Customizer.withDefaults()))
////                        .cors().disable()
////                        .csrf().disable();
//                return http.build();
//
////            http
////                    .authorizeHttpRequests((authorize) -> authorize
////                            .requestMatchers("/products").hasAuthority("admin")
////                            .anyRequest().authenticated()
////                    )
////                    .oauth2ResourceServer((oauth2) -> oauth2.jwt(Customizer.withDefaults()));
//        //    return http.build();
//        }
        }
}
