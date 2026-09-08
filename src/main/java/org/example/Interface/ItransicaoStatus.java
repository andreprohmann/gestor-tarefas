package org.example.Interface;

import org.example.Entidade.StatusTarefas;
import org.example.Entidade.Tarefa;

public interface ItransicaoStatus {
    void validar(Tarefa tarefa, StatusTarefas novoStatus);
}
