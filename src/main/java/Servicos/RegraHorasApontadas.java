package Servicos;

import Entidade.StatusTarefas;
import Entidade.Tarefa;
import Interface.ItransicaoStatus;

public class RegraHorasApontadas implements ItransicaoStatus {
    @Override
    public void validar(Tarefa tarefa, StatusTarefas novoStatus){
        if(novoStatus == StatusTarefas.CONCLUIDO && tarefa.getApontamentos().isEmpty()){
            throw new IllegalArgumentException("A Tarefa nao pode ser concluida sem horas apontadas");
        }
    }
}
