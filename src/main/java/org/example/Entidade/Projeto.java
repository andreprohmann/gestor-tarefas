package org.example.Entidade;

public class Projeto {
    private String nome;
    private Usuario responsavel; //relaciona o usuário responsavel

    public Projeto (String nome, Usuario responsavel){
        this.nome = nome;
        this.responsavel = responsavel;
    }

    public String getNome() {
        return nome;
    }

    public Usuario getResponsavel() {
        return responsavel;
    }
}
