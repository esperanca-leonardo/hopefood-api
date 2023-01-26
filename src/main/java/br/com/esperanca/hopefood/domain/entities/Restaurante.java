package br.com.esperanca.hopefood.domain.entities;

import br.com.esperanca.hopefood.domain.embeddables.Endereco;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Restaurante {

  @Id
  @EqualsAndHashCode.Include
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank
  private String nome;

  @PositiveOrZero
  private BigDecimal taxaFrete;

  @Valid
  @NotNull
  @JoinColumn
  @ManyToOne(fetch = FetchType.LAZY)
  @JsonIgnoreProperties("hibernateLazyInitializer")
  private Cozinha cozinha;

  @JsonIgnore
  @ManyToMany
  @JoinTable(
    joinColumns = @JoinColumn(name = "restaurante_id")
  )
  private List<FormaPagamento> formaPagamento = new ArrayList<>();

  @JsonIgnore
  @OneToMany(mappedBy = "restaurante")
  private List<Produto> produto = new ArrayList<>();

  @Embedded
  @JsonIgnore
  private Endereco endereco;

  @CreationTimestamp
  private LocalDateTime dataCadastro;

  @UpdateTimestamp
  private LocalDateTime dataAtualizacao;
}
