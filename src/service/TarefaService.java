package service;

import model.Tarefa;
import model.Usuario;
import model.Notificador;
import java.util.ArrayList;
import java.util.List;

public class TarefaService {
    private final List<Tarefa> tarefas = new ArrayList<>();
    private Notificador notificador;

    public TarefaService() {
        this.notificador = new Notificador();
    }

    public void adicionarTarefa(Tarefa tarefa) {
        tarefas.add(tarefa);
        notificador.enviar("Nova tarefa criada: '" + tarefa.getTitulo() + "' com prazo até " + tarefa.getDataFim());
    }

    public void marcarConcluida(Tarefa tarefa) {
        tarefa.setConcluida(true);
        notificador.enviar("Tarefa concluída: '" + tarefa.getTitulo() + "'");
    }

    public void removerTarefa(Tarefa tarefa) {
        tarefas.remove(tarefa);
        notificador.enviar("Tarefa removida: '" + tarefa.getTitulo() + "'");
    }

    public List<Tarefa> listarTarefas() {
        return new ArrayList<>(tarefas);
    }

    public void limparTarefas() {
        int quantidade = tarefas.size();
        tarefas.clear();
        notificador.enviar("Todas as " + quantidade + " tarefas foram removidas");
    }
}