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

    public Multa() {}

    public int getCodigoMulta() {
        return codigoMulta;
    }

    public void setCodigoMulta(int codigoMulta) {
        this.codigoMulta = codigoMulta;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public int getPontuacao() {
        return pontuacao;
    }

    public void setPontuacao(int pontuacao) {
        this.pontuacao = pontuacao;
    }

    public Veiculo getVeiculoMultado() {
        return veiculoMultado;
    }

    public void setVeiculoMultado(Veiculo veiculoMultado) {
        this.veiculoMultado = veiculoMultado;
    }

    @Override
    public String toString() {
        String dados = String.format("Código da multa: %d\nValor: %.2f\nPontuação: %d\nVeículo multado:\n%s", codigoMulta, valor, pontuacao, veiculoMultado.toString());

        return dados;
    }
}
