package controller;

import model.Diario;
import model.Usuario;
import java.time.LocalDate;
import java.util.List;

public class DiarioController {
    private Usuario usuario;

    public DiarioController(Usuario usuario) {
        this.usuario = usuario;
    }

    public boolean criarRegistroDiario(LocalDate data, String conteudo) {
        if (conteudo == null || conteudo.isBlank()) {
            return false;
        }

        Diario diario = new Diario(data, conteudo);
        usuario.adicionarDiario(diario);
        return true;
    }

    public boolean removerRegistroDiario(Diario diario) {
        if (diario != null) {
            return usuario.getDiarios().remove(diario);
        }
        return false;
    }

    public List<Diario> listarRegistrosDiario() {
        return usuario.getDiarios();
    }
}