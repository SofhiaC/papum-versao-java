package controller;

import model.Tarefa;
import model.Usuario;
import service.TarefaService;
import java.time.LocalDate;
import java.util.List;

public class TarefaController {
    private Usuario usuario;
    private TarefaService tarefaService;

    public TarefaController(Usuario usuario) {
        this.usuario = usuario;
        this.tarefaService = new TarefaService();
    }

    public void criarTarefa(String titulo, String descricao, LocalDate inicio, LocalDate prazo) {
        Tarefa tarefa = new Tarefa(titulo, descricao, inicio, prazo);
        tarefaService.adicionarTarefa(tarefa); // Notificação acontece AQUI no Service
        usuario.adicionarTarefa(tarefa);
    }

    public void concluirTarefa(Tarefa tarefa) {
        tarefaService.marcarConcluida(tarefa); // Notificação acontece AQUI no Service
    }

    public void removerTarefa(Tarefa tarefa) {
        tarefaService.removerTarefa(tarefa); // Notificação acontece AQUI no Service
        usuario.getTarefas().remove(tarefa);
    }

    public List<Tarefa> listarTarefas() {
        return usuario.getTarefas();
    }

    public void limparTodasTarefas() {
        tarefaService.limparTarefas(); // Notificação acontece AQUI no Service
        usuario.getTarefas().clear();
    }
}