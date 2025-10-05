package service;

import model.Tarefa;
import model.Usuario;
import java.util.ArrayList;
import java.util.List;

public class TarefaService {
    private final List<Tarefa> tarefas = new ArrayList<>();

    public void adicionarTarefa(Tarefa tarefa){
        tarefas.add(tarefa);
    }

    public List<Tarefa> listarTarefas(){
        return new ArrayList<>(tarefas);
    }

    public void marcarConcluida(Tarefa tarefa){
        tarefa.setConcluida(true);
    }

    public void removerTarefa(Tarefa tarefa){
        tarefas.remove(tarefa);
    }

    //acho esse daqui meio inútil
    public void limparTarefaUsuario(Usuario usuario){
        tarefas.clear();
    }
}
