    package com.web.finalproject.config;

    import com.web.finalproject.service.UserService;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.context.annotation.Bean;
    import org.springframework.context.annotation.Configuration;
    import org.springframework.security.authentication.AuthenticationManager;
    import org.springframework.security.authentication.AuthenticationProvider;
    import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
    import org.springframework.security.config.Customizer;
    import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
    import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
    import org.springframework.security.config.annotation.web.builders.HttpSecurity;
    import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
    import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
    import org.springframework.security.config.http.SessionCreationPolicy;
    import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
    import org.springframework.security.crypto.password.PasswordEncoder;
    import org.springframework.security.web.SecurityFilterChain;
    import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

    @Configuration
    @EnableWebSecurity
    @EnableGlobalMethodSecurity(prePostEnabled = true)
    public class SpringSecurityConfig {

        @Autowired
        private UserService userService;
        @Autowired
        private AuthEntryPointJwt authEntryPointJwt;
        @Autowired
        private PasswordEncoder passwordEncoder;

        @Bean
        public JwtTokenFilter jwtTokenFilter() { return new JwtTokenFilter(); }

        @Bean
        public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
            return authConfig.getAuthenticationManager();
        }

        @Bean
        public AuthenticationProvider authenticationProvider() {
            DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
            authProvider.setUserDetailsService(userService);
            authProvider.setPasswordEncoder(passwordEncoder);
            return authProvider;
        }

        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
            httpSecurity.csrf(csrf -> csrf.disable())
                    .cors(Customizer.withDefaults())
                    .authorizeHttpRequests(auth -> auth
                            .requestMatchers("/api/token").permitAll()
                            .requestMatchers("/api/admin/**").hasAuthority("ROLE_ADMIN")
                            .anyRequest().permitAll()
                    )
                    .sessionManagement(httpSecuritySessionManagementConfigurer -> {
                        httpSecuritySessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
                    })
                    .exceptionHandling(httpSecurityExceptionHandlingConfigurer -> {
                        httpSecurityExceptionHandlingConfigurer.authenticationEntryPoint(authEntryPointJwt);
                    });
            httpSecurity.authenticationProvider(authenticationProvider());
            httpSecurity.addFilterBefore(jwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
            return httpSecurity.build();
        }
    }
