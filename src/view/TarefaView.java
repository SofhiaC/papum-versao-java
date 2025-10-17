package view;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import model.Tarefa;
import model.ListagemTarefa;
import service.TarefaService;
import model.Sessao;
import model.Usuario;

import java.time.LocalDate;

public class TarefaView {
    private Usuario usuario;
    private TarefaService service;
    private ListagemTarefa listagem;

    public TarefaView() {
        // Obtém o usuário da Sessao
        this.usuario = Sessao.getInstance().getUsuario();
        if (this.usuario == null) {
            throw new IllegalStateException("Nenhum usuário logado para a TarefaView.");
        }
        this.service = new TarefaService();
        this.listagem = new ListagemTarefa(service);
    }

    public void start(Stage stage) {
        Label lblTitulo = new Label("Título:");
        TextField txtTitulo = new TextField();

        Label lblDescricao = new Label("Descrição:");
        TextArea txtDescricao = new TextArea();

        Label lblDataInicio = new Label("Data Início:");
        DatePicker dpInicio = new DatePicker(LocalDate.now());

        Label lblPrazo = new Label("Prazo:");
        DatePicker dpPrazo = new DatePicker(LocalDate.now().plusDays(1));

        Button btnAdicionar = new Button("Adicionar Tarefa");
        ListView<Tarefa> listView = new ListView<>();
        atualizarLista(listView);

        btnAdicionar.setOnAction(e -> {
            String titulo = txtTitulo.getText();
            String descricao = txtDescricao.getText();
            LocalDate inicio = dpInicio.getValue();
            LocalDate prazo = dpPrazo.getValue();

            if (titulo.isBlank()) {
                showAlert("Erro", "O título é obrigatório!");
                return;
            }

            if (prazo.isBefore(inicio)) {
                showAlert("Erro", "O prazo não pode ser anterior à data de início!");
                return;
            }

            Tarefa tarefa = new Tarefa(titulo, descricao, inicio, prazo);

            usuario.adicionarTarefa(tarefa);

            service.adicionarTarefa(tarefa);

            atualizarLista(listView);

            txtTitulo.clear();
            txtDescricao.clear();
        });

        Button btnConcluir = new Button("Marcar Concluída");
        btnConcluir.setOnAction(e -> {
            Tarefa selecionada = listView.getSelectionModel().getSelectedItem();
            if (selecionada != null) {
                // CORREÇÃO: Marcar como concluída no USUÁRIO também
                selecionada.setConcluida(true);
                service.marcarConcluida(selecionada);
                atualizarLista(listView);
            } else {
                showAlert("Aviso", "Selecione uma tarefa!");
            }
        });

        Button btnRemover = new Button("Remover");
        btnRemover.setOnAction(e -> {
            Tarefa selecionada = listView.getSelectionModel().getSelectedItem();
            if (selecionada != null) {
                // CORREÇÃO: Remover do USUÁRIO também
                usuario.getTarefas().remove(selecionada);
                service.removerTarefa(selecionada);
                atualizarLista(listView);
            } else {
                showAlert("Aviso", "Selecione uma tarefa!");
            }
        });

        Button btnVoltar = new Button("Voltar");
        btnVoltar.setOnAction(e -> {
            stage.close();
            new MainView().start(new Stage());
        });

        VBox layout = new VBox(10,
                lblTitulo, txtTitulo,
                lblDescricao, txtDescricao,
                new HBox(10, new VBox(lblDataInicio, dpInicio), new VBox(lblPrazo, dpPrazo)),
                btnAdicionar,
                new Label("Tarefas:"),
                listView,
                new HBox(10, btnConcluir, btnRemover, btnVoltar)
        );
        layout.setPadding(new Insets(15));

        Scene scene = new Scene(layout, 500, 600);
        stage.setScene(scene);
        stage.setTitle("Gerenciador de Tarefas - " + usuario.getNome());
        stage.show();
    }

    private void atualizarLista(ListView<Tarefa> listView){
        // CORREÇÃO: Buscar as tarefas do USUÁRIO em vez do service
        listView.getItems().setAll(usuario.getTarefas());

        // Se quiser manter o Template Method, pode usar:
        // listView.getItems().setAll(listagem.listar());
        // Mas precisa ajustar o ListagemTarefa para usar usuario.getTarefas()
    }

    private void showAlert(String titulo, String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}