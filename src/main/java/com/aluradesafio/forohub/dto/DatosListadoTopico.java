package com.aluradesafio.forohub.dto;

import com.aluradesafio.forohub.model.Topico;
import java.time.LocalDateTime;

public record DatosListadoTopico(
        Long id,
        String titulo,
        String mensaje,
        LocalDateTime fechaCreacion,
        String estatus,
        Long autorId,
        Long cursoId
) {
    public DatosListadoTopico(Topico topico) {
        this(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFechaCreacion(),
                topico.getEstatus(),
                topico.getAutor().getId(),
                topico.getCurso().getId()
        );
    }
}