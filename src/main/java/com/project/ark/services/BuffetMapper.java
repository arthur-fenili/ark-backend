package com.project.ark.services;

import com.project.ark.dto.request.BuffetRequestDTO;
import com.project.ark.dto.response.BuffetResponseDTO;
import com.project.ark.models.Buffet;
import org.springframework.stereotype.Service;

@Service
public class BuffetMapper {
    public static Buffet requestToBuffet(BuffetRequestDTO dto) {
        Buffet buffet = new Buffet();
        buffet.setNome(dto.nome());
        buffet.setEndereco(dto.endereco());
        buffet.setNomeDono(dto.nomeDono());
        return buffet;
    }

    public static BuffetResponseDTO buffetToResponse(Buffet buffet) {
        return new BuffetResponseDTO(
                buffet.getId(),
                buffet.getNome(),
                buffet.getEndereco(),
                buffet.getNomeDono()
        );
    }
}
