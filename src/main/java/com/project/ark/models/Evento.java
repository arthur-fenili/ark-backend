package com.project.ark.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ARK_Evento")
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdEvento")
    private Long id;

    @Column(name = "Descricao", nullable = false, length = 255)
    private String descricao;

    @Column(name = "Data", nullable = false)
    private LocalDate data;

    @Column(name = "Horario", nullable = false)
    private LocalTime horario;

    @ManyToOne
    @JoinColumn(name = "IdBuffet", referencedColumnName = "IdBuffet")
    private Buffet buffet;

    @ManyToOne
    @JoinColumn(name = "IdDecoracao", referencedColumnName = "IdDecoracao")
    private Decoracao decoracao;

    public String getDataHoraFormatada() {
        return data.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + " às " +
                horario.format(DateTimeFormatter.ofPattern("HH:mm"));
    }
}
