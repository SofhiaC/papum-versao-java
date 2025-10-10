package view;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import model.Tarefa;
import model.Usuario;
import controller.TarefaController;
import java.time.LocalDate;

public class TarefaView {
    private Usuario usuario;
    private TarefaController controller;

    public TarefaView(Usuario usuario) {
        this.usuario = usuario;
        this.controller = new TarefaController(usuario);
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
        listView.getItems().setAll(controller.listarTarefas());

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

            // View chama Controller - notificação acontece no Service
            controller.criarTarefa(titulo, descricao, inicio, prazo);
            listView.getItems().setAll(controller.listarTarefas());

            txtTitulo.clear();
            txtDescricao.clear();
        });

        Button btnConcluir = new Button("Marcar Concluída");
        btnConcluir.setOnAction(e -> {
            Tarefa selecionada = listView.getSelectionModel().getSelectedItem();
            if (selecionada != null) {
                controller.concluirTarefa(selecionada); // Notificação no Service
                listView.refresh();
            } else {
                showAlert("Aviso", "Selecione uma tarefa!");
            }
        });

        Button btnRemover = new Button("Remover");
        btnRemover.setOnAction(e -> {
            Tarefa selecionada = listView.getSelectionModel().getSelectedItem();
            if (selecionada != null) {
                controller.removerTarefa(selecionada); // Notificação no Service
                listView.getItems().setAll(controller.listarTarefas());
            } else {
                showAlert("Aviso", "Selecione uma tarefa!");
            }
        });

        Button btnVoltar = new Button("Voltar");
        btnVoltar.setOnAction(e -> {
            stage.close();
            new MainView(usuario).start(new Stage());
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

    private void showAlert(String titulo, String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}