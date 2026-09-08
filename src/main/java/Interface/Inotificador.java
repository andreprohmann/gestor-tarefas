package Interface;

import Entidade.Usuario;

public interface Inotificador {
    void notificar(Usuario usuario, String mensagem);
}
