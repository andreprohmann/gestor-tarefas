package org.example.Servicos;

import org.example.Entidade.StatusTarefas;
import org.example.Entidade.Tarefa;
import org.example.Interface.Inotificador;
import org.example.Interface.ItarefaRepositorio;
import org.example.Interface.ItransicaoStatus;

import java.util.List;

public class MovimentoTarefas {
    private List<ItransicaoStatus> regras;
    private Inotificador notificador;
    private ItarefaRepositorio repositorio;

    public MovimentoTarefas(List<ItransicaoStatus> regras, Inotificador notificador, ItarefaRepositorio repositorio){
        this.regras = regras;
        this.notificador = notificador;
        this.repositorio = repositorio;
    }
    public void  criar_SalvarNovaTarefa(Tarefa tarefa){
        //Salva no banco como Listado
        repositorio.salvar(tarefa);
    }
    public void moverTarefa(Tarefa tarefa, StatusTarefas novoStatus){
        //1º Executa todas as regras
        for(ItransicaoStatus regra : regras){
            regra.validar(tarefa,novoStatus);
        }
        //2º modifica a entidade
        tarefa.alterarStatus(novoStatus);
        //3º Atualiza no banco de dados
        repositorio.atualizarStatus(tarefa);
        //4º dispara a notificação
        notificador.notificar(tarefa.getResponsavel(), "Sua tarefa mudou para status " + novoStatus);
    }
}
