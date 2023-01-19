package br.com.esperanca.hopefood.domain.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Produto {
  @Id
  @EqualsAndHashCode.Include
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String nome;

  private String descricao;

  private BigDecimal preco;

  private boolean ativo;

  @ManyToOne
  private Restaurante restaurante;

  @CreationTimestamp
  private LocalDateTime dataCadastro;

  @UpdateTimestamp
  private LocalDateTime dataAtualizacao;
}
