package br.com.esperanca.hopefood.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Data
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Cozinha {

  @Id
  @EqualsAndHashCode.Include
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String nome;

  @JsonIgnore
  @OneToMany(mappedBy = "cozinha")
  private List<Restaurante> restaurantes = new ArrayList<>();

  @CreationTimestamp
  private LocalDateTime dataCadastro;

  @UpdateTimestamp
  private LocalDateTime dataAtualizacao;
}
