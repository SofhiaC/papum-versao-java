package model;

import service.TarefaService;
import java.util.List;
import java.util.stream.Collectors;

public class ListagemTarefa {
    protected TarefaService service;

    public ListagemTarefa(TarefaService service) {
        this.service = service;
    }

    // Template Method
    public List<Tarefa> listar() {
        return processarLista(service.listarTarefas());
    }

    // Hook method para subclasses modificarem o comportamento
    protected List<Tarefa> processarLista(List<Tarefa> tarefas) {
        return tarefas;
    }
}