package controller;

import model.Usuario;
import model.Sessao;
import service.LoginService;

public class UsuarioController {
    private LoginService loginService;

    public UsuarioController() {
        this.loginService = LoginService.getInstance();
    }

    public Usuario login(String nome, String senha) {
        Usuario usuario = loginService.login(nome, senha);
        if (usuario != null) {
            Sessao.getInstance().setUsuario(usuario);
        }
        return usuario;
    }

    public void logout() {
        Sessao.getInstance().logout();
    }

    public Usuario getUsuarioLogado() {
        return Sessao.getInstance().getUsuario();
    }

    public boolean isUsuarioLogado() {
        return Sessao.getInstance().isLogado();
    }
}