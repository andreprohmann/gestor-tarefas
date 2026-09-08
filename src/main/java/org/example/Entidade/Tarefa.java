package org.example.Entidade;

import java.util.ArrayList;
import java.util.List;

public class Tarefa {
    private String titulo;
    private StatusTarefas status;
    private Usuario responsavel;
    private List<ApontamentoHora> apontamentos;

    public Tarefa (String titulo, Usuario responsavel){
        this.titulo = titulo;
        this.responsavel = responsavel;
        this.status = StatusTarefas.LISTADO;
        this.apontamentos = new ArrayList<>();
    }

    public void alterarStatus(StatusTarefas novoStatus){
        this.status=novoStatus;
    }
    public void adicionarApontamento(ApontamentoHora apontamento){
        if(this.status == StatusTarefas.CONCLUIDO){
            throw new IllegalStateException("Não foi possivel apontar horas em tarefas concluidas");
        }
        this.apontamentos.add(apontamento);
    }


    public String getTitulo() {
        return titulo;
    }

    public StatusTarefas getStatus() {
        return status;
    }

    public Usuario getResponsavel() {
        return responsavel;
    }

    public List<ApontamentoHora> getApontamentos() {
        return apontamentos;
    }
}

