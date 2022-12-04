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

  public Veiculo() {

  }

  public String getPlaca() {
    return placa;
  }

  public void setPlaca(String placa) {
    this.placa = placa;
  }

  public int getAno() {
    return ano;
  }

  public void setAno(int ano) {
    this.ano = ano;
  }

  public String getModelo() {
    return modelo;
  }

  public void setModelo(String modelo) {
    this.modelo = modelo;
  }

  public String getMarca() {
    return marca;
  }

  public void setMarca(String marca) {
    this.marca = marca;
  }

  public Condutor getCondutor() {
    return condutor;
  }

  public void setCondutor(Condutor condutor) {
    this.condutor = condutor;
  }

  public List<Multa> getMultas() {
    return multas;
  }

  public void setMultas(List<Multa> multas) {
    this.multas = multas;
  }
}
