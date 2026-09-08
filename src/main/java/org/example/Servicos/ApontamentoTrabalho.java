package org.example.Servicos;

import org.example.Entidade.ApontamentoHora;
import org.example.Entidade.Tarefa;

public class ApontamentoTrabalho {
    public void registrarTrabalho(Tarefa tarefa, ApontamentoHora apontamento){
        if(apontamento.getHoras() <=0){
            throw new IllegalArgumentException("As horas apontadas devem ser maiores que 0!!");
        }
        //Delega para a entidade (respeitando o encapsulamento)
        tarefa.adicionarApontamento(apontamento);
        System.out.println("Apontamento de " + apontamento.getHoras() + "h registrado com sucesso!");
    }
}
