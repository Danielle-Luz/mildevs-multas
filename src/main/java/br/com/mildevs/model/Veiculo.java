package br.com.mildevs.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Veiculo {
  @Id
  @Column(length = 7)
  String placa;
  int ano;
  @Column(length = 30)
  String modelo;
  @Column(length = 30)
  String marca;
  @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST, optional = false)
  @JoinColumn(name = "fk_condutor", referencedColumnName = "numeroCnh")
  Condutor condutor;
  @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "veiculoMultado")
  List<Multa> multas;
}
