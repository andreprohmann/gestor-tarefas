package org.example.Servicos;

import org.example.Entidade.Usuario;
import org.example.Interface.Inotificador;

public class NotificarEmaiil implements Inotificador {
    @Override
    public void notificar(Usuario usuario, String mensagem){
        System.out.println("Enviando E-mail para " + usuario.getEmail() + ": " + mensagem);
    }
}
