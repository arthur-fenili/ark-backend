package com.project.ark.controllers;

import com.project.ark.dto.request.DecoracaoRequestDTO;
import com.project.ark.dto.response.DecoracaoResponseDTO;
import com.project.ark.models.Decoracao;
import com.project.ark.repositories.DecoracaoRepository;
import com.project.ark.services.DecoracaoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/decoracoes")
public class DecoracaoController {

    @Autowired
    private DecoracaoRepository decoracaoRepository;

    private final DecoracaoMapper decoracaoMapper;

    // Injeção do mapper para transformação de dados
    public DecoracaoController(DecoracaoMapper decoracaoMapper) {
        this.decoracaoMapper = decoracaoMapper;
    }

    // Endpoint para listar todas as decorações
    @GetMapping
    public ResponseEntity<List<DecoracaoResponseDTO>> listarDecoracoes() {
        List<Decoracao> decoracoes = decoracaoRepository.findAll();
        List<DecoracaoResponseDTO> decoracaoResponseDTOs = decoracoes.stream()
                .map(DecoracaoMapper::decoracaoToResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(decoracaoResponseDTOs);
    }

    // Endpoint para buscar uma decoração por ID
    @GetMapping("/{id}")
    public ResponseEntity<DecoracaoResponseDTO> buscarDecoracaoPorId(@PathVariable Long id) {
        Optional<Decoracao> decoracaoOptional = decoracaoRepository.findById(id);
        if (decoracaoOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        DecoracaoResponseDTO decoracaoResponseDTO = DecoracaoMapper.decoracaoToResponse(decoracaoOptional.get());
        return ResponseEntity.ok(decoracaoResponseDTO);
    }

    // Endpoint para criar uma nova decoração
    @PostMapping
    public ResponseEntity<DecoracaoResponseDTO> criarDecoracao(@RequestBody DecoracaoRequestDTO decoracaoRequestDTO) {
        Decoracao decoracao = DecoracaoMapper.requestToDecoracao(decoracaoRequestDTO);
        Decoracao decoracaoSalva = decoracaoRepository.save(decoracao);
        DecoracaoResponseDTO decoracaoResponseDTO = DecoracaoMapper.decoracaoToResponse(decoracaoSalva);
        return ResponseEntity.status(201).body(decoracaoResponseDTO);
    }

    // Endpoint para atualizar uma decoração existente
    @PutMapping("/{id}")
    public ResponseEntity<DecoracaoResponseDTO> atualizarDecoracao(@PathVariable Long id, @RequestBody DecoracaoRequestDTO decoracaoRequestDTO) {
        Optional<Decoracao> decoracaoOptional = decoracaoRepository.findById(id);
        if (decoracaoOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Decoracao decoracaoExistente = decoracaoOptional.get();
        Decoracao decoracaoAtualizada = DecoracaoMapper.requestToDecoracao(decoracaoRequestDTO);
        decoracaoAtualizada.setId(decoracaoExistente.getId()); // Preservando o ID da decoração existente
        Decoracao decoracaoSalva = decoracaoRepository.save(decoracaoAtualizada);
        DecoracaoResponseDTO decoracaoResponseDTO = DecoracaoMapper.decoracaoToResponse(decoracaoSalva);
        return ResponseEntity.ok(decoracaoResponseDTO);
    }

    // Endpoint para deletar uma decoração
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarDecoracao(@PathVariable Long id) {
        Optional<Decoracao> decoracaoOptional = decoracaoRepository.findById(id);
        if (decoracaoOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        decoracaoRepository.delete(decoracaoOptional.get());
        return ResponseEntity.noContent().build();
    }
}
