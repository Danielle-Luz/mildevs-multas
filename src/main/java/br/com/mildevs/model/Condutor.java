package br.com.mildevs.model;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

public class Condutor {
  @Id
  @Column(name = "numero_cnh")
  private int numeroCnh;
  @Column(name = "data_emissao_cnh")
  private LocalDate dataEmissaoCnh;
  @Column(name = "orgao_emissor", length = 5)
  private String orgaoEmissor;
  private int pontuacao;
  @OneToMany(mappedBy = "condutor")
  private List<Veiculo> veiculos;

  public Condutor() {

  }

  public int getNumeroCnh() {
    return numeroCnh;
  }

  public void setNumeroCnh(int numeroCnh) {
    this.numeroCnh = numeroCnh;
  }

  public LocalDate getDataEmissaoCnh() {
    return dataEmissaoCnh;
  }

  public void setDataEmissaoCnh(LocalDate dataEmissaoCnh) {
    this.dataEmissaoCnh = dataEmissaoCnh;
  }

  public String getOrgaoEmissor() {
    return orgaoEmissor;
  }

  public void setOrgaoEmissor(String orgaoEmissor) {
    this.orgaoEmissor = orgaoEmissor;
  }

  public int getPontuacao() {
    return pontuacao;
  }

  public void setPontuacao(int pontuacao) {
    this.pontuacao = pontuacao;
  }

  public List<Veiculo> getVeiculos() {
    return veiculos;
  }

  public void setVeiculos(List<Veiculo> veiculos) {
    this.veiculos = veiculos;
  }
  
}
