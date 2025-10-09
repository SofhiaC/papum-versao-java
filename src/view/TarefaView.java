package view;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import model.Tarefa;
import model.Usuario;
import service.TarefaService;

import java.time.LocalDate;

public class TarefaView {

    private Usuario usuario;
    private TarefaService tarefaService;

    public TarefaView(Usuario usuario) {
        this.usuario = usuario;
        this.tarefaService = new TarefaService();
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

            if (prazo.isBefore(inicio)) {
                showAlert("Erro", "O prazo não pode ser anterior à data de início!");
                return;
            }

            Tarefa tarefa = new Tarefa(titulo, descricao, inicio, prazo);
            tarefaService.adicionarTarefa(tarefa);
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
                tarefaService.marcarConcluida(selecionada); // Dispara notificação
                listView.refresh();
            } else {
                showAlert("Aviso", "Selecione uma tarefa para marcar como concluída!");
            }
        });

        Button btnRemover = new Button("Remover");
        btnRemover.setOnAction(e -> {
            Tarefa selecionada = listView.getSelectionModel().getSelectedItem();
            if (selecionada != null) {
                tarefaService.removerTarefa(selecionada); // Dispara notificação
                usuario.getTarefas().remove(selecionada);
                listView.getItems().setAll(usuario.getTarefas());
            } else {
                showAlert("Aviso", "Selecione uma tarefa para remover!");
            }
        });

        Button btnLimparTudo = new Button("Limpar Todas");
        btnLimparTudo.setOnAction(e -> {
            if (!usuario.getTarefas().isEmpty()) {
                Alert confirmacao = new Alert(Alert.AlertType.CONFIRMATION);
                confirmacao.setTitle("Confirmação");
                confirmacao.setHeaderText("Limpar todas as tarefas");
                confirmacao.setContentText("Tem certeza que deseja remover TODAS as tarefas?");

                if (confirmacao.showAndWait().get() == ButtonType.OK) {
                    tarefaService.limparTarefaUsuario(usuario); // Dispara notificação
                    usuario.getTarefas().clear();
                    listView.getItems().setAll(usuario.getTarefas());
                }
            } else {
                showAlert("Aviso", "Não há tarefas para limpar!");
            }
        });

        Button btnVoltar = new Button("Voltar");
        btnVoltar.setOnAction(e -> {
            stage.close();
            new MainView(usuario).start(new Stage());
        });


        VBox formLayout = new VBox(10,
                lblTitulo, txtTitulo,
                lblDescricao, txtDescricao,
                new HBox(10, new VBox(lblDataInicio, dpInicio), new VBox(lblPrazo, dpPrazo)),
                btnAdicionar
        );
        formLayout.setPadding(new Insets(10));

        VBox listLayout = new VBox(10,
                new Label("Tarefas:"),
                listView,
                new HBox(10, btnConcluir, btnRemover, btnLimparTudo)
        );
        listLayout.setPadding(new Insets(10));

        VBox mainLayout = new VBox(10, formLayout, listLayout, btnVoltar);
        mainLayout.setPadding(new Insets(15));

        Scene scene = new Scene(mainLayout, 500, 650);
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