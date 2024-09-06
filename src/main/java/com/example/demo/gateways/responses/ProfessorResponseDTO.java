package com.example.demo.gateways.responses;

import com.example.demo.domains.Aluno;
import com.example.demo.domains.Pessoa;
import lombok.Builder;

import java.util.List;

@Builder
public record ProfessorResponseDTO(
        String materia,
        String primeiroNome,
        String sobrenome,
        List<Aluno> aluno
) {
}
