package com.example.demo.gateways;

import com.example.demo.domains.Aluno;
import com.example.demo.domains.Pessoa;
import com.example.demo.domains.Professor;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.example.demo.gateways.requests.ProfessorRequestDTO;
import com.example.demo.gateways.responses.ProfessorResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/professor/fiap")
@RequiredArgsConstructor
public class ProfessorController {

    private final ProfessorRepository professorRepository;
    private final AlunoRepository alunoRepository;

    @PostMapping
    public ProfessorResponseDTO criaProfessor(@RequestBody ProfessorRequestDTO professorRequestDTO) {
        Professor professorASerCriado = Professor.builder()
                .materia(professorRequestDTO.materia())
                .alunos(professorRequestDTO.alunoList())
                .pessoa(Pessoa.builder().
                        primeiroNome(professorRequestDTO.primeiroNome()).
                        sobrenome(professorRequestDTO.sobrenome()).
                        documento(professorRequestDTO.documento()).
                        build()).build();

        Professor professorCriado = professorRepository.save(professorASerCriado);


        return ProfessorResponseDTO.builder()
                .materia(professorCriado.getMateria())
                .primeiroNome(professorCriado.getPessoa().getPrimeiroNome())
                .sobrenome(professorCriado.getPessoa().getSobrenome())
                .aluno(professorCriado.getAlunos()).build();
    }

    @PatchMapping("/{professorId}/alunos")
    public Optional<Professor> associaAlunos(@PathVariable UUID professorId, @RequestBody List<UUID> listaDeIdsDeAlunos) {
        List<Aluno> allById = alunoRepository.findAllById(listaDeIdsDeAlunos);
        Optional<Professor> professor = professorRepository.findById(professorId.toString());
        professor.ifPresent(professor1 -> {
            professor1.setAlunos(allById);
            professorRepository.save(professor1);
        });

        return professor;
    }
}
