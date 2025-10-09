package service;

import model.Tarefa;
import model.Usuario;
import java.util.ArrayList;
import java.util.List;

public class TarefaService {
    private final List<Tarefa> tarefas = new ArrayList<>();
    private Notificador notificador;

    public TarefaService() {

        this.notificador = new NotificadorComTimestamp(new NotificadorBase());
    }

    public TarefaService(Notificador notificador) {
        this.notificador = notificador;
    }

    public void adicionarTarefa(Tarefa tarefa){
        tarefas.add(tarefa);

        String mensagem = "Nova tarefa criada: '" + tarefa.getTitulo() +
                "' com prazo até " + tarefa.getDataFim();
        notificador.enviar(mensagem);
    }

    public List<Tarefa> listarTarefas(){
        return new ArrayList<>(tarefas);
    }

    public void marcarConcluida(Tarefa tarefa){
        tarefa.setConcluida(true);

        String mensagem = "Tarefa concluída: '" + tarefa.getTitulo() + "'";
        notificador.enviar(mensagem);
    }

    public void removerTarefa(Tarefa tarefa){
        tarefas.remove(tarefa);

        String mensagem = "Tarefa removida: '" + tarefa.getTitulo() + "'";
        notificador.enviar(mensagem);
    }

    public void limparTarefaUsuario(Usuario usuario){
        int quantidade = tarefas.size();
        tarefas.clear();

        String mensagem = "Todas as " + quantidade + " tarefas foram removidas";
        notificador.enviar(mensagem);
    }
}