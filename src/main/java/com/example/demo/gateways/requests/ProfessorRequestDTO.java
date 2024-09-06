package com.example.demo.gateways.requests;

import com.example.demo.domains.Aluno;
import com.example.demo.domains.Pessoa;

import java.util.List;
import java.util.UUID;

public record ProfessorRequestDTO(
        String materia,
        String primeiroNome,
        String sobrenome,
        String documento,
        List<Aluno> alunoList

) {
}
