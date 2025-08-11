package com.aluradesafio.forohub.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record DatosAutenticacionUsuario(
        @NotBlank(message = "El correo electrónico es obligatorio")
        @Email(message = "El formato del correo electrónico no es válido")
        String correoElectronico,
        @NotBlank(message = "La contraseña es obligatoria")
        String contrasena
) {}