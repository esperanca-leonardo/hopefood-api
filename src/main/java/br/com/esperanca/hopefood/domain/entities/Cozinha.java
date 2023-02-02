package br.com.esperanca.hopefood.domain.entities;

import br.com.esperanca.hopefood.core.validators.groups.CozinhaValidator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Data
@Entity
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Cozinha {

  @Id
  @NotNull(groups = CozinhaValidator.class)
  @EqualsAndHashCode.Include
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank
  private String nome;

  @JsonIgnore
  @OneToMany(mappedBy = "cozinha")
  private List<Restaurante> restaurantes = new ArrayList<>();

  @CreationTimestamp
  private LocalDateTime dataCadastro;

  @UpdateTimestamp
  private LocalDateTime dataAtualizacao;

  public Cozinha(String nome) {
    this.nome = nome;
  }
}
