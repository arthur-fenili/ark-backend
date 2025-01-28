package com.project.ark.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ARK_Decoracao")
public class Decoracao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdDecoracao")
    private Long id;

    @Column(name = "Tema")
    private String tema;

    @Column(name = "Descricao")
    private String descricao;


}
