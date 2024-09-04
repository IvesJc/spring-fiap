package com.example.demo.domains;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_pessoa")
public class Pessoa {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;

  private String primeiroNome;
  private String sobrenome;
  private String documento;
}
