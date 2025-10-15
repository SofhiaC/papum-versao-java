package service;

import model.Usuario;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * login e usuários na memória são tratados nessa service
 */

public class LoginService {

    private static LoginService instance;
    private List<Usuario> usuarios;

    //Construtor privado para evitar instâncias externas
    private LoginService(){
        usuarios = new ArrayList<>();
        carregarUsuarios();
    }

    //Método para realizar a única instatncia
    public static LoginService getInstance() {
        if (instance == null) {
            instance = new LoginService();
        }
        return instance;
    }

    private void carregarUsuarios(){
        usuarios.add(new Usuario(1, "Alice", "123"));
        usuarios.add(new Usuario(2, "Barbara", "abc"));
        usuarios.add(new Usuario(3, "Joãozinho", "senha"));
    }

    public Usuario login(String nome, String senha){
        for (Usuario u : usuarios){
            if (u.getNome().equals(nome) && u.getSenha().equals(senha)){
                return u;
            }
        }
        return null;
    }

    public List<Usuario> listarUsuarios(){
        return usuarios;
    }
}
