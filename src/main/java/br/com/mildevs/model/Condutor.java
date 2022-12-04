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
}
