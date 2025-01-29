package com.project.ark.services;

import com.project.ark.dto.request.DecoracaoRequestDTO;
import com.project.ark.dto.response.DecoracaoResponseDTO;
import com.project.ark.models.Decoracao;
import org.springframework.stereotype.Service;

@Service
public class DecoracaoMapper {
    public static Decoracao requestToDecoracao(DecoracaoRequestDTO dto) {
        Decoracao decoracao = new Decoracao();
        decoracao.setTema(dto.tema());
        decoracao.setDescricao(dto.descricao());
        return decoracao;
    }

    public static DecoracaoResponseDTO decoracaoToResponse(Decoracao entity) {
        return new DecoracaoResponseDTO(
                entity.getId(),
                entity.getTema(),
                entity.getDescricao()
        );
    }
}
