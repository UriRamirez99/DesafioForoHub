package com.aluradesafio.forohub.repository;

import com.aluradesafio.forohub.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Curso, Long> {
}