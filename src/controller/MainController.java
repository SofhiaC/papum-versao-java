package controller;

import model.Usuario;
import model.Sessao;

public class MainController {
    private Sessao sessao;

    public MainController() {
        this.sessao = Sessao.getInstance();
    }

    public Usuario getUsuarioLogado() {
        return sessao.getUsuario();
    }

    public String getNomeUsuario() {
        Usuario usuario = getUsuarioLogado();
        return usuario != null ? usuario.getNome() : "Usuário";
    }

    public void logout() {
        sessao.logout();
    }

    public boolean isUsuarioLogado() {
        return sessao.isLogado();
    }

    // Métodos para verificar se o usuário tem dados
    public boolean temTarefas() {
        Usuario usuario = getUsuarioLogado();
        return usuario != null && !usuario.getTarefas().isEmpty();
    }

    public boolean temDiarios() {
        Usuario usuario = getUsuarioLogado();
        return usuario != null && !usuario.getDiarios().isEmpty();
    }

    public int getQuantidadeTarefas() {
        Usuario usuario = getUsuarioLogado();
        return usuario != null ? usuario.getTarefas().size() : 0;
    }

    public int getQuantidadeDiarios() {
        Usuario usuario = getUsuarioLogado();
        return usuario != null ? usuario.getDiarios().size() : 0;
    }
}