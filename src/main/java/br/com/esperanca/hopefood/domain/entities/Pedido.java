package br.com.esperanca.hopefood.domain.entities;

import br.com.esperanca.hopefood.domain.embeddables.Endereco;
import br.com.esperanca.hopefood.domain.enuns.StatusPedido;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Pedido {

  @Id
  @EqualsAndHashCode.Include
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private BigDecimal subtotal;

  private BigDecimal taxaFrete;

  private BigDecimal valorTotal;

  private LocalDateTime dataConfirmacao;

  private LocalDateTime dataCancelamento;

  private LocalDateTime dataEntrega;

  @ManyToOne
  private FormaPagamento formaPagamento;

  @ManyToOne
  private Restaurante restaurante;

  @ManyToOne
  private Usuario usuario;

  @Embedded
  private Endereco endereco;

  private StatusPedido statusPedido;

  @OneToMany(mappedBy = "pedido")
  private List<ItemPedido> itemPedidos = new ArrayList<>();

  @CreationTimestamp
  private LocalDateTime dataCadastro;

  @UpdateTimestamp
  private LocalDateTime dataAtualizacao;
}
