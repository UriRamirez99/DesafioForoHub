package com.aluradesafio.forohub.controller;

import com.aluradesafio.forohub.dto.DatosAutenticacionUsuario;
import com.aluradesafio.forohub.infra.security.TokenService;
import com.aluradesafio.forohub.model.Usuario;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacionController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity autenticarUsuario(@RequestBody @Valid DatosAutenticacionUsuario datosAutenticacionUsuario) {
        Authentication token = new UsernamePasswordAuthenticationToken(
                datosAutenticacionUsuario.correoElectronico(),
                datosAutenticacionUsuario.contrasena()
        );
        var usuarioAutenticado = authenticationManager.authenticate(token);
        var JWTtoken = tokenService.generarToken((Usuario) usuarioAutenticado.getPrincipal());
        return ResponseEntity.ok(JWTtoken);
    }
}
