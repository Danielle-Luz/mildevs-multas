package br.com.mildevs.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

import java.time.LocalDate;

@Entity
public class Condutor {

    @Id
    @Column(name = "numero_cnh")
    private int numeroCnh;

    @Column(name = "data_emissao_cnh")
    private LocalDate dataEmissaoCnh;

    @Column(name = "orgao_emissor", length = 10, nullable = false)
    private String orgaoEmissor;

    @Column(nullable = false)
    private int pontuacao;

    @OneToOne(mappedBy = "condutor", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private Veiculo veiculo = null;

    public Condutor() {}

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

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    @Override
    public String toString() {
        String dados = String.format("CNH: %d\nData de emissão: %s\nOrgão emissor: %s\nPontuação da CNH: %d", numeroCnh, dataEmissaoCnh.toString(), orgaoEmissor, pontuacao);

        if(veiculo != null) {
            dados += "\nVeículo:\n" + veiculo.toString();
        }

        return dados;
    }
}