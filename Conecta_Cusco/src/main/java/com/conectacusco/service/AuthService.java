package com.conectacusco.service;

import com.conectacusco.dto.JwtResponse;
import com.conectacusco.dto.LoginRequest;
import com.conectacusco.dto.RegisterRequest;
import com.conectacusco.model.Role;
import com.conectacusco.model.Usuario;
import com.conectacusco.repository.RoleRepository;
import com.conectacusco.repository.UsuarioRepository;
import com.conectacusco.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AuthService {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtil jwtUtil;

    public JwtResponse authenticateUser(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getNombreUsuario(), loginRequest.getContrasena()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtil.generateJwtToken(authentication);

        Usuario usuarioDetails = (Usuario) authentication.getPrincipal(); // Aquí deberías Castear a tu clase Usuario
        // Para simplificar, asumimos que el Principal es directamente tu Usuario o tiene métodos para obtener su información
        // Idealmente, deberías crear una clase UserDetailsImpl que implemente UserDetails y tenga tu Usuario

        String rol = usuarioDetails.getRol().getNombre().name(); // Obtener el nombre del rol

        return new JwtResponse(jwt, "Bearer", usuarioDetails.getId(),
                usuarioDetails.getNombreUsuario(), usuarioDetails.getEmail(), rol);
    }

    public Usuario registerUser(RegisterRequest signUpRequest) {
        if (usuarioRepository.existsByNombreUsuario(signUpRequest.getNombreUsuario())) {
            throw new RuntimeException("Error: El nombre de usuario ya está en uso!");
        }

        if (usuarioRepository.existsByEmail(signUpRequest.getEmail())) {
            throw new RuntimeException("Error: El email ya está en uso!");
        }

        // Crear nuevo usuario
        Usuario user = new Usuario();
        user.setNombreUsuario(signUpRequest.getNombreUsuario());
        user.setEmail(signUpRequest.getEmail());
        user.setContrasena(encoder.encode(signUpRequest.getContrasena()));
        user.setNombreCompleto(signUpRequest.getNombreCompleto());
        user.setTelefono(signUpRequest.getTelefono());
        user.setDireccion(signUpRequest.getDireccion());
        user.setUrlFotoPerfil(signUpRequest.getUrlFotoPerfil());

        Role role = roleRepository.findByNombre(signUpRequest.getRol())
                .orElseThrow(() -> new RuntimeException("Error: Rol no encontrado."));
        user.setRol(role);

        return usuarioRepository.save(user);
    }
}