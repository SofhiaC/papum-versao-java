package model;

import service.TarefaService;
import java.util.List;

public abstract class ListagemTarefa {
    protected TarefaService service;

    public ListagemTarefa(TarefaService service) {
        this.service = service;
    }

    // Template Method
    public final List<Tarefa> listar() {
        return processarLista(service.listarTarefas());
    }

    // Hook method — as subclasses decidem o filtro
    protected abstract List<Tarefa> processarLista(List<Tarefa> tarefas);
}
