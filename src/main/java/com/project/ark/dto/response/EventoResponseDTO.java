package com.project.ark.dto.response;


import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public record EventoResponseDTO(
        Long id,
        String descricao,
        String dataHoraFormatada, // Exemplo: "28/01/2025 às 13:30"
        BuffetResponseDTO buffet,
        DecoracaoResponseDTO decoracao
) {
    public EventoResponseDTO(Long id, String descricao, LocalDate data, LocalTime horario, BuffetResponseDTO buffet, DecoracaoResponseDTO decoracao) {
        this(id, descricao, formatDataHora(data, horario), buffet, decoracao);
    }

    private static String formatDataHora(LocalDate data, LocalTime horario) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        return data.format(dateFormatter) + " às " + horario.format(timeFormatter);
    }
}
