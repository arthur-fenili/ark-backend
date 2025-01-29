package com.project.ark.services;

import com.project.ark.dto.request.EventoRequestDTO;
import com.project.ark.dto.response.EventoResponseDTO;
import com.project.ark.models.Evento;
import com.project.ark.models.Buffet;
import com.project.ark.models.Decoracao;

public class EventoMapper {

    public Evento requestToEvento(EventoRequestDTO dto, Buffet buffet, Decoracao decoracao) {
        Evento evento = new Evento();
        evento.setDescricao(dto.descricao());
        evento.setData(dto.data());
        evento.setHorario(dto.horario());
        evento.setBuffet(buffet);
        evento.setDecoracao(decoracao);
        return evento;
    }

    public EventoResponseDTO eventoToResponse(Evento entity) {
        return new EventoResponseDTO(
                entity.getId(),
                entity.getDescricao(),
                entity.getData(),
                entity.getHorario(),
                BuffetMapper.buffetToResponse(entity.getBuffet()),
                DecoracaoMapper.decoracaoToResponse(entity.getDecoracao())
        );
    }
}
