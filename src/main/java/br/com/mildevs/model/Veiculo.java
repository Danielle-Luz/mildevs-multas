package br.com.mildevs.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Veiculo {

    @Id
    @Column(length = 7)
    private String placa;

    private int ano;

    @Column(length = 30)
    private String modelo;

    @Column(length = 30)
    private String marca;

    @OneToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "fk_condutor", referencedColumnName = "numero_cnh")
    private Condutor condutor;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "veiculoMultado")
    private List<Multa> multas = new ArrayList<>();
    public Veiculo() {}

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

    @Override
    public String toString() {
        String dados = String.format("Placa: %s\nAno: %d\nModelo: %s\nMarca: %s", placa, ano, modelo, marca);

        return dados;
    }
}
