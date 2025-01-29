package com.project.ark.controllers;

import com.project.ark.dto.request.BuffetRequestDTO;
import com.project.ark.dto.response.BuffetResponseDTO;
import com.project.ark.models.Buffet;
import com.project.ark.repositories.BuffetRepository;
import com.project.ark.services.BuffetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/buffets")
public class BuffetController {

    @Autowired
    private BuffetRepository buffetRepository;

    private final BuffetMapper buffetMapper;

    // Injeção do mapper para transformação de dados
    public BuffetController(BuffetMapper buffetMapper) {
        this.buffetMapper = buffetMapper;
    }

    // Endpoint para listar todos os buffets
    @GetMapping
    public ResponseEntity<List<BuffetResponseDTO>> listarBuffets() {
        List<Buffet> buffets = buffetRepository.findAll();
        List<BuffetResponseDTO> buffetResponseDTOs = buffets.stream()
                .map(BuffetMapper::buffetToResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(buffetResponseDTOs);
    }

    // Endpoint para buscar um buffet por ID
    @GetMapping("/{id}")
    public ResponseEntity<BuffetResponseDTO> buscarBuffetPorId(@PathVariable Long id) {
        Optional<Buffet> buffetOptional = buffetRepository.findById(id);
        if (buffetOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        BuffetResponseDTO buffetResponseDTO = BuffetMapper.buffetToResponse(buffetOptional.get());
        return ResponseEntity.ok(buffetResponseDTO);
    }

    // Endpoint para criar um novo buffet
    @PostMapping
    public ResponseEntity<BuffetResponseDTO> criarBuffet(@RequestBody BuffetRequestDTO buffetRequestDTO) {
        Buffet buffet = BuffetMapper.requestToBuffet(buffetRequestDTO);
        Buffet buffetSalvo = buffetRepository.save(buffet);
        BuffetResponseDTO buffetResponseDTO = BuffetMapper.buffetToResponse(buffetSalvo);
        return ResponseEntity.status(201).body(buffetResponseDTO);
    }

    // Endpoint para atualizar um buffet existente
    @PutMapping("/{id}")
    public ResponseEntity<BuffetResponseDTO> atualizarBuffet(@PathVariable Long id, @RequestBody BuffetRequestDTO buffetRequestDTO) {
        Optional<Buffet> buffetOptional = buffetRepository.findById(id);
        if (buffetOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Buffet buffetExistente = buffetOptional.get();
        Buffet buffetAtualizado = BuffetMapper.requestToBuffet(buffetRequestDTO);
        buffetAtualizado.setId(buffetExistente.getId()); // Preservando o ID do buffet existente
        Buffet buffetSalvo = buffetRepository.save(buffetAtualizado);
        BuffetResponseDTO buffetResponseDTO = BuffetMapper.buffetToResponse(buffetSalvo);
        return ResponseEntity.ok(buffetResponseDTO);
    }

    // Endpoint para deletar um buffet
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarBuffet(@PathVariable Long id) {
        Optional<Buffet> buffetOptional = buffetRepository.findById(id);
        if (buffetOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        buffetRepository.delete(buffetOptional.get());
        return ResponseEntity.noContent().build();
    }
}
