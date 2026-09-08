package Interface;

import Entidade.Tarefa;

public interface ItarefaRepositorio {
    void salvar(Tarefa tarefa);
    void atualizarStatus(Tarefa tarefa);
}
