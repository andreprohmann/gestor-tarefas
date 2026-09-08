package org.example.Interface;

import org.example.Entidade.Tarefa;

public interface ItarefaRepositorio {
    void salvar(Tarefa tarefa);
    void atualizarStatus(Tarefa tarefa);
}
