package com.project.ark.controllers;

import com.project.ark.dto.request.EventoRequestDTO;
import com.project.ark.dto.response.EventoResponseDTO;
import com.project.ark.models.Evento;
import com.project.ark.models.Buffet;
import com.project.ark.models.Decoracao;
import com.project.ark.repositories.EventoRepository;
import com.project.ark.repositories.BuffetRepository;
import com.project.ark.repositories.DecoracaoRepository;
import com.project.ark.services.EventoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/eventos")
public class EventoController {

    @Autowired
    private EventoRepository eventoRepository;

    @Autowired
    private BuffetRepository buffetRepository;

    @Autowired
    private DecoracaoRepository decoracaoRepository;

    private final EventoMapper eventoMapper = new EventoMapper();

    // Criar evento
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EventoResponseDTO criarEvento(@RequestBody EventoRequestDTO dto) {
        Buffet buffet = buffetRepository.findById(dto.idBuffet()).orElseThrow(() -> new RuntimeException("Buffet não encontrado"));
        Decoracao decoracao = decoracaoRepository.findById(dto.idDecoracao()).orElseThrow(() -> new RuntimeException("Decoração não encontrada"));

        Evento evento = eventoMapper.requestToEvento(dto, buffet, decoracao);
        Evento eventoSalvo = eventoRepository.save(evento);

        return eventoMapper.eventoToResponse(eventoSalvo);
    }

    // Listar eventos
    @GetMapping
    public List<EventoResponseDTO> listarEventos() {
        List<Evento> eventos = eventoRepository.findAll();
        return eventos.stream()
                .map(eventoMapper::eventoToResponse)
                .collect(Collectors.toList());
    }

    // Consultar evento por ID
    @GetMapping("/{id}")
    public EventoResponseDTO buscarEvento(@PathVariable Long id) {
        Evento evento = eventoRepository.findById(id).orElseThrow(() -> new RuntimeException("Evento não encontrado"));
        return eventoMapper.eventoToResponse(evento);
    }

    // Atualizar evento
    @PutMapping("/{id}")
    public EventoResponseDTO atualizarEvento(@PathVariable Long id, @RequestBody EventoRequestDTO dto) {
        Buffet buffet = buffetRepository.findById(dto.idBuffet()).orElseThrow(() -> new RuntimeException("Buffet não encontrado"));
        Decoracao decoracao = decoracaoRepository.findById(dto.idDecoracao()).orElseThrow(() -> new RuntimeException("Decoração não encontrada"));

        Evento evento = eventoRepository.findById(id).orElseThrow(() -> new RuntimeException("Evento não encontrado"));
        evento.setDescricao(dto.descricao());
        evento.setData(dto.data());
        evento.setHorario(dto.horario());
        evento.setBuffet(buffet);
        evento.setDecoracao(decoracao);

        Evento eventoSalvo = eventoRepository.save(evento);

        return eventoMapper.eventoToResponse(eventoSalvo);
    }

    // Deletar evento
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deletarEvento(@PathVariable Long id){
        if (!eventoRepository.existsById(id)) {
            throw new IllegalArgumentException("Evento não encontrado com ID: " + id);
        }
        eventoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
