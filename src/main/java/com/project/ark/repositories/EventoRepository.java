package com.project.ark.repositories;

import com.project.ark.models.Evento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Long> {
    // Buscar eventos ordenados por data
    List<Evento> findAllByData(LocalDate data);
}
