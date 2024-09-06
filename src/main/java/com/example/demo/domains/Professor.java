package com.example.demo.domains;

import jakarta.persistence.*;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_prof")
public class Professor {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String materia;

    @OneToOne(cascade = CascadeType.ALL)
    private Pessoa pessoa;

    @OneToMany(
            mappedBy = "professor"
    )
    // mappedBy = é utilizado quando temos um relacionamento bidirecional mapeado entre duas classes e
    // informar o nome do atributo da classe utilizada no mapeamento na outra ponta do relacionamento.
    private List<Aluno> alunos;
}
