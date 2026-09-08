package org.example.Servicos;

import org.example.Entidade.StatusTarefas;
import org.example.Entidade.Tarefa;
import org.example.Interface.ItransicaoStatus;

public class RegraHorasApontadas implements ItransicaoStatus {
    @Override
    public void validar(Tarefa tarefa, StatusTarefas novoStatus){
        if(novoStatus == StatusTarefas.CONCLUIDO && tarefa.getApontamentos().isEmpty()){
            throw new IllegalArgumentException("A Tarefa nao pode ser concluida sem horas apontadas");
        }
    }
}
