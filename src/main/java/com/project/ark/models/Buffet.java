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
@Table(name = "ARK_Buffet")
public class Buffet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdBuffet")
    private Long id;

    @Column(name = "Nome")
    private String nome;

    @Column(name = "NomeDono")
    private String nomeDono;

    @Column(name = "Endereco")
    private String endereco;

}
