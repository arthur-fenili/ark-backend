package com.project.ark.dto.request;

import java.time.LocalDate;
import java.time.LocalTime;

public record EventoRequestDTO(
        String descricao,
        LocalDate data,
        LocalTime horario,
        Long idBuffet,
        Long idDecoracao
) {}
