package com.aluradesafio.forohub.repository;

import com.aluradesafio.forohub.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    UserDetails findByCorreoElectronico(String correoElectronico);
}