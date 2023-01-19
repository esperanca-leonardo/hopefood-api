package br.com.esperanca.hopefood.domain.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Grupo {
  @Id
  @EqualsAndHashCode.Include
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String nome;

  @ManyToMany
  @JoinTable(
    joinColumns = @JoinColumn(name = "grupo_id")
  )
  private List<Permissao> permissao = new ArrayList<>();

  @UpdateTimestamp
  private LocalDateTime dataCadastro;

  @CreationTimestamp
  private LocalDateTime dataAtualizacao;
}
