package com.aluradesafio.forohub.infra.security;

import com.aluradesafio.forohub.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AutenticacionService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Implementaremos la lógica de búsqueda aquí
        // Por ahora, solo devuelve una llamada al repositorio
        return usuarioRepository.findByCorreoElectronico(username);
    }
}