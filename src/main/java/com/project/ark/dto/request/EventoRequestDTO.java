package com.project.ark.dto.request;

import com.project.ark.dto.response.BuffetResponseDTO;
import com.project.ark.dto.response.DecoracaoResponseDTO;

import java.time.LocalDate;
import java.time.LocalTime;

public record EventoRequestDTO(String descricao,
                               LocalDate data,
                               LocalTime horario,
                               BuffetResponseDTO buffet,
                               DecoracaoResponseDTO decoracao) {
}
