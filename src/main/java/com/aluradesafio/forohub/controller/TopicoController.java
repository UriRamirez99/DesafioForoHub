package com.aluradesafio.forohub.controller;

import com.aluradesafio.forohub.dto.DatosActualizarTopico;
import com.aluradesafio.forohub.dto.DatosListadoTopico;
import com.aluradesafio.forohub.dto.DatosRegistroTopico;
import com.aluradesafio.forohub.model.Topico;
import com.aluradesafio.forohub.model.Usuario;
import com.aluradesafio.forohub.model.Curso;
import com.aluradesafio.forohub.repository.CursoRepository;
import com.aluradesafio.forohub.repository.TopicoRepository;
import com.aluradesafio.forohub.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @PostMapping
    public ResponseEntity<Void> registrarTopico(@RequestBody @Valid DatosRegistroTopico datosRegistroTopico, UriComponentsBuilder uriBuilder) {
        // 1. Validar si el tópico es un duplicado
        if (topicoRepository.existsByTituloAndMensaje(datosRegistroTopico.titulo(), datosRegistroTopico.mensaje())) {
            return ResponseEntity.badRequest().build(); // 400 Bad Request si es duplicado
        }

        // 2. Validar si el autor y el curso existen
        Usuario autor = usuarioRepository.findById(datosRegistroTopico.idAutor())
                .orElseThrow(() -> new IllegalArgumentException("Autor no encontrado"));
        Curso curso = cursoRepository.findById(datosRegistroTopico.idCurso())
                .orElseThrow(() -> new IllegalArgumentException("Curso no encontrado"));

        // 3. Convertir el DTO a la entidad Topico
        Topico topico = new Topico(
                datosRegistroTopico.titulo(),
                datosRegistroTopico.mensaje(),
                autor,
                curso
        );

        // 4. Guardar la entidad en la base de datos
        topicoRepository.save(topico);

        // 5. Devolver una respuesta 201 Created con la URI del nuevo tópico
        URI uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping
    public ResponseEntity<Page<DatosListadoTopico>> listarTopicos(@PageableDefault(size = 10, sort = {"fechaCreacion"}) Pageable paginacion) {
        Page<Topico> topicos = topicoRepository.findAll(paginacion);
        Page<DatosListadoTopico> datos = topicos.map(DatosListadoTopico::new);
        return ResponseEntity.ok(datos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosListadoTopico> detallarTopico(@PathVariable Long id) {
        Optional<Topico> topicoOptional = topicoRepository.findById(id);

        if (topicoOptional.isPresent()) {
            Topico topico = topicoOptional.get();
            return ResponseEntity.ok(new DatosListadoTopico(topico));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DatosListadoTopico> actualizarTopico(@PathVariable Long id, @RequestBody DatosActualizarTopico datosActualizarTopico) {
        // 1. Buscar el tópico por ID usando findById()
        Optional<Topico> optionalTopico = topicoRepository.findById(id);

        // 2. Manejar el caso de tópico no encontrado
        if (optionalTopico.isEmpty()) {
            return ResponseEntity.notFound().build(); // Devuelve 404 Not Found
        }

        // 3. Si el tópico existe, obtenerlo del Optional y aplicar las actualizaciones
        Topico topico = optionalTopico.get();
        topico.actualizar(datosActualizarTopico);

        // La transacción se encargará de guardar los cambios al finalizar el método

        // 4. Devolver la respuesta con el tópico actualizado
        return ResponseEntity.ok(new DatosListadoTopico(topico));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> eliminarTopico(@PathVariable Long id) {
        // 1. Verificar si el tópico existe
        Optional<Topico> optionalTopico = topicoRepository.findById(id);

        if (optionalTopico.isPresent()) {
            // 2. Si existe, lo eliminamos por su ID
            topicoRepository.deleteById(id);
            // 3. Devolvemos una respuesta 204 No Content
            return ResponseEntity.noContent().build();
        } else {
            // 4. Si no existe, devolvemos un 404 Not Found
            return ResponseEntity.notFound().build();
        }
    }
}



