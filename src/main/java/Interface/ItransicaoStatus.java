package Interface;

import Entidade.StatusTarefas;
import Entidade.Tarefa;

public interface ItransicaoStatus {
    void validar(Tarefa tarefa, StatusTarefas novoStatus);
}
