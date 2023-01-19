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
public class ItemPedido {
  @Id
  @EqualsAndHashCode.Include
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private Integer quantidade;

  private BigDecimal valorUnitario;

  private BigDecimal precoTotal;

  private String observacao;

  @ManyToOne
  private Pedido pedido;

  @ManyToOne
  private Produto produto;

  @CreationTimestamp
  private LocalDateTime dataCadastro;

  @UpdateTimestamp
  private LocalDateTime dataAtualizacao;
}