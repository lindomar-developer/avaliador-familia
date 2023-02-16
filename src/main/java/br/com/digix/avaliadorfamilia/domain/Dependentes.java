package br.com.digix.avaliadorfamilia.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "DEPENDENTE")
public class Dependentes {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "dependente_gen")
    @SequenceGenerator(name = "dependente_gen", sequenceName = "DEPENDENTE_SEQ", allocationSize = 1)
    private Long id;

    @Column(name = "NOME")
    private String nome;

    @Column(name = "IDADE")
    private Integer idade;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_FAMILIA")
    private Familias familias;
}
