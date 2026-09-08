package org.example.Repositorio;

import org.example.Entidade.Tarefa;
import org.example.Interface.ItarefaRepositorio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class TarefasSQLite implements ItarefaRepositorio {

    private final String url = "jdbc:sqlite:gestor_tarefas.db";

    public TarefasSQLite(){
        criarTabelaSeNaoExistir();
    }
    private void criarTabelaSeNaoExistir(){
        String sql = "CREATE TABLE IF NOT EXISTS tarefas (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "titulo TEXT NOT NULL, " +
                    "status TEXT NOT NULL)";
        try(Connection conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement()){

        }catch (Exception e){
            System.out.println("Erro ao criar tabela SQLite" + e.getLocalizedMessage());
        }
    }
    @Override
    public void salvar(Tarefa tarefa){
        String sql = "INSERT INTO tarefas(titulo, status) VALUES (?, ?)";
        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement pstmt = conn.prepareStatement(url)){
                pstmt.setString(1, tarefa.getTitulo());
                pstmt.setString(2, tarefa.getStatus().name());
                pstmt.executeUpdate();

                System.out.println("Tarefa salva no SQLite com sucesso!");
        }catch (Exception e){
            System.out.println("Erro ao salvar no banco: " + e.getMessage());
        }
    }
    @Override
    public void atualizarStatus(Tarefa tarefa){
        String sql = "UPDATE tarefas SET status = ? WHERE titulo = ?";

        try (Connection conn = DriverManager.getConnection(url);
            PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1, tarefa.getStatus().name());
            pstmt.setString(1,tarefa.getTitulo());
            pstmt.executeUpdate();

            System.out.println("Status Atualizado no banco de dados com suscesso!");

        }catch (Exception e){
            System.out.println("Erro ao atualizar no banco" + e.getMessage());
        }
    }
}
