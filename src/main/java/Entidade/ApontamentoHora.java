package Entidade;

import java.time.LocalDate;

public class ApontamentoHora {
    private double horas;
    private String descricao;
    private LocalDate data;

    public ApontamentoHora(double horas, String descricao){
        this.horas = horas;
        this.descricao = descricao;
        this.data = LocalDate.new();
    }

    public double getHoras() {
        return horas;
    }

    public String getDescricao() {
        return descricao;
    }

    public LocalDate getData() {
        return data;
    }
}

