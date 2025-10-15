package model;

public class Sessao {
    private static Sessao instance;
    private Usuario usuario;

    private Sessao() {} // evita instancias exterans

    //oMétodo estático para obter a instância
    public static Sessao getInstance() {
        if (instance  == null) {
            instance  = new Sessao();
        }
        return instance ;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    // Gerencia o usuário logado

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void logout() {
        this.usuario = null; // Limpa o usuário ao fazer logout
    }

    public boolean isLogado() {
        return this.usuario != null;
    }
}
