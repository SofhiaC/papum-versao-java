package view;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import model.Tarefa;
import model.Usuario;

import java.time.LocalDate;

public class TarefaView {

    private Usuario usuario;

    public TarefaView(Usuario usuario) {
        this.usuario = usuario;
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

        // inicializa a lista com tarefas do usuário logado
        listView.getItems().setAll(usuario.getTarefas());

        btnAdicionar.setOnAction(e -> {
            String titulo = txtTitulo.getText();
            String descricao = txtDescricao.getText();
            LocalDate inicio = dpInicio.getValue();
            LocalDate prazo = dpPrazo.getValue();

            if (titulo.isBlank()) {
                showAlert("Erro", "O título é obrigatório!");
                return;
            }

            Tarefa tarefa = new Tarefa(titulo, descricao, inicio, prazo);
            usuario.adicionarTarefa(tarefa);
            listView.getItems().setAll(usuario.getTarefas());

            txtTitulo.clear();
            txtDescricao.clear();
        });

        Button btnConcluir = new Button("Marcar Concluída");
        btnConcluir.setOnAction(e -> {
            Tarefa selecionada = listView.getSelectionModel().getSelectedItem();
            if (selecionada != null) {
                selecionada.setConcluida(true);
                listView.refresh();
            }
        });

        Button btnRemover = new Button("Remover");
        btnRemover.setOnAction(e -> {
            Tarefa selecionada = listView.getSelectionModel().getSelectedItem();
            if (selecionada != null) {
                usuario.getTarefas().remove(selecionada);
                listView.getItems().setAll(usuario.getTarefas());
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
                lblDataInicio, dpInicio,
                lblPrazo, dpPrazo,
                btnAdicionar,
                new Label("Tarefas:"),
                listView,
                new HBox(10, btnConcluir, btnRemover, btnVoltar)
        );
        layout.setPadding(new Insets(15));

        Scene scene = new Scene(layout, 400, 600);
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
