package br.com.mildevs.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Multa {
  @Id
  @GeneratedValue
  @Column(name = "codigo_multa")
  private int codigoMulta;
  @Column(nullable = false)
  private double valor;
  @Column(nullable = false)
  private int pontuacao;
  @JoinColumn(name = "fk_veiculo", referencedColumnName = "placa")
  @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST, optional = false)
  private Veiculo veiculoMultado;
}
