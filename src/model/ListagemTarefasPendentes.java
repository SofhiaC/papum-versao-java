package model;

import service.TarefaService;
import java.util.List;
import java.util.stream.Collectors;

public class ListagemTarefasPendentes extends ListagemTarefa {
    public ListagemTarefasPendentes(TarefaService service) {
        super(service);
    }

    @Override
    protected List<Tarefa> processarLista(List<Tarefa> tarefas) {
        return Sessao.getInstance().getUsuario().getTarefas()
                .stream()
                .filter(t -> !t.isConcluida())
                .collect(Collectors.toList());
    }
}
