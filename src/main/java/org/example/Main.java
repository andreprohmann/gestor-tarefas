package org.example;

import org.example.Entidade.*;
import org.example.Interface.Inotificador;
import org.example.Interface.ItarefaRepositorio;
import org.example.Interface.ItransicaoStatus;
import org.example.Repositorio.TarefasSQLite;
import org.example.Servicos.ApontamentoTrabalho;
import org.example.Servicos.MovimentoTarefas;
import org.example.Servicos.NotificarEmaiil;
import org.example.Servicos.RegraHorasApontadas;

import java.util.Arrays;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main(String[] args) {
        System.out.println("---INICIANDO SISTEMA de GESTÂO de TAREFAS ---\n");

        //1º Instacia as dependencias da infraestrutura a regras
        ItarefaRepositorio repositorio = new TarefasSQLite();
        Inotificador notificador = new NotificarEmaiil();
        ItransicaoStatus regraHoras = new RegraHorasApontadas();

        //Agrupamento as regras em uma lista (injeção)
        List<ItransicaoStatus> regra = Arrays.asList(regraHoras);
        //2º Instaciando os serviços e intando as dependencias
        MovimentoTarefas servicoMovimentacao = new MovimentoTarefas(regra, notificador, repositorio);
        ApontamentoTrabalho servicoApontamento = new ApontamentoTrabalho();

        //3º Criando as Entidades do Dominio
        Usuario dev = new Usuario("Andre Prohmann", "andre.prohmann@gmail.com");
        Projeto projeto = new Projeto("Sistema de Gestão", dev);
        Tarefa tarefa = new Tarefa("Modelar Banco de dados", dev);

        //Salvando a nova tarefa SQLite
        servicoMovimentacao.criar_SalvarNovaTarefa(tarefa);
        // 4. SIMULAÇÃO 1: Tentando concluir a tarefa direto (DEVE FALHAR por causa da regra)
        System.out.println("\n--- Tentativa 1: Mover para CONCLUIDO sem horas apontadas ---");
        try {
            servicoMovimentacao.moverTarefa(tarefa, StatusTarefas.CONCLUIDO);
        } catch (Exception e) {
            System.out.println("BLOQUEADO COM SUCESSO: " + e.getMessage());
        }

        // 5. SIMULAÇÃO 2: Movendo para Em Andamento e registrando horas
        System.out.println("\n--- Movendo para DOING e Apontando Horas ---");
        servicoMovimentacao.moverTarefa(tarefa, StatusTarefas.FAZENDO);

        ApontamentoHora apontamento = new ApontamentoHora(3.5, "Mapeamento inicial das tabelas");
        servicoApontamento.registrarTrabalho(tarefa, apontamento);

        // 6. SIMULAÇÃO 3: Concluindo a tarefa agora com sucesso
        System.out.println("\n--- Tentativa 2: Mover para DONE com horas apontadas ---");
        try {
            servicoMovimentacao.moverTarefa(tarefa, StatusTarefas.CONCLUIDO);
            System.out.println("SUCESSO! Tarefa concluída e atualizada no banco.");
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }

        System.out.println("\n--- FIM DOS TESTES ---");
    }

}
