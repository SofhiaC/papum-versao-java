package model;

import java.util.List;

public class ListagemDiario {
    protected Usuario usuario;

    public ListagemDiario(Usuario usuario) {
        this.usuario = usuario;
    }

    // Template Method
    public List<Diario> listar() {
        return processarLista(usuario.getDiarios());
    }

    // Hook method
    protected List<Diario> processarLista(List<Diario> diarios) {
        return diarios;
    }
}