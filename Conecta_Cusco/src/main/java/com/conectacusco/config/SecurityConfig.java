package com.conectacusco.config;

import com.conectacusco.security.JwtAuthEntryPoint;
import com.conectacusco.security.JwtAuthFilter;
import com.conectacusco.repository.UsuarioRepository;
import com.conectacusco.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity // Habilita la seguridad basada en métodos con @PreAuthorize, etc.
public class SecurityConfig {

    @Autowired
    private JwtAuthEntryPoint unauthorizedHandler;

    @Autowired
    private JwtAuthFilter jwtAuthFilter; // Asegúrate de que este bean se inyecte

    @Autowired
    private UsuarioRepository usuarioRepository; // Inyecta el repositorio de usuarios

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            Usuario user = usuarioRepository.findByNombreUsuario(username)
                    .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));
            // Convierte tu objeto Usuario a una instancia de UserDetails (o a tu propia implementación de UserDetails)
            // Para simplificar, aquí creamos una instancia simple de UserDetails con el nombre de usuario y contraseña.
            // En una aplicación real, deberías cargar los roles y usar un constructor más completo.
            return new org.springframework.security.core.userdetails.User(
                    user.getNombreUsuario(),
                    user.getContrasena(),
                    new java.util.ArrayList<>() // Sin roles específicos por ahora, solo para que funcione
            );
        };
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .exceptionHandling(exception -> exception.authenticationEntryPoint(unauthorizedHandler))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/**").permitAll() // Rutas de autenticación permitidas para todos
                        .requestMatchers("/api/publicaciones/**").permitAll() // Permitir acceso a publicaciones para todos (ajustar luego con roles)
                        .anyRequest().authenticated() // Cualquier otra solicitud requiere autenticación
                );

        http.authenticationProvider(authenticationProvider());
        http.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}