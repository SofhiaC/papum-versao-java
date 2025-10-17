package view;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import model.Diario;
import model.ListagemDiario;
import controller.DiarioController;  // Import do Controller
import model.Sessao;
import model.Usuario;

import java.time.LocalDate;
import java.util.List;

public class DiarioView {
    private Usuario usuario;
    private DiarioController controller;  // Usando o Controller
    private ListagemDiario listagem;

    public DiarioView(){
        this.usuario = Sessao.getInstance().getUsuario();
        if (this.usuario == null) {
            throw new IllegalStateException("Nenhum usuário logado para a DiarioView.");
        }
        this.controller = new DiarioController(usuario);  // Instancia o Controller
        this.listagem = new ListagemDiario(usuario);
    }

    public void start(Stage stage){
        Label lblData = new Label("Data:");
        DatePicker dpData = new DatePicker(LocalDate.now());

        Label lblConteudo = new Label("Como foi seu dia?");
        TextArea txtConteudo = new TextArea();
        txtConteudo.setPromptText("Escreva aqui seu registro diário...");
        txtConteudo.setWrapText(true);  // Melhora a visualização do texto

        Button btnSalvar = new Button("Salvar");
        ListView<Diario> listView = new ListView<>();
        atualizarLista(listView);

        btnSalvar.setOnAction(e -> {
            LocalDate data = dpData.getValue();
            String conteudo = txtConteudo.getText();

            if (conteudo.isBlank()) {
                showAlert("Erro", "O conteúdo do diário não pode estar vazio!");
                return;
            }

            // Usa o Controller para criar o registro
            boolean sucesso = controller.criarRegistroDiario(data, conteudo);

            if (sucesso) {
                atualizarLista(listView);
                txtConteudo.clear();
                showAlert("Sucesso", "Registro salvo com sucesso!");
            } else {
                showAlert("Erro", "Erro ao salvar o registro!");
            }
        });

        Button btnRemover = new Button("Remover Selecionado");
        btnRemover.setOnAction(e -> {
            Diario selecionado = listView.getSelectionModel().getSelectedItem();
            if (selecionado != null) {
                // Usa o Controller para remover
                boolean removido = controller.removerRegistroDiario(selecionado);
                if (removido) {
                    atualizarLista(listView);
                    showAlert("Sucesso", "Registro removido com sucesso!");
                }
            } else {
                showAlert("Aviso", "Selecione um registro para remover!");
            }
        });

        Button btnVoltar = new Button("Voltar");
        btnVoltar.setOnAction(e -> {
            stage.close();
            new MainView().start(new Stage());
        });

        // Botão para editar registro (opcional)
        Button btnEditar = new Button("Editar Selecionado");
        btnEditar.setOnAction(e -> {
            Diario selecionado = listView.getSelectionModel().getSelectedItem();
            if (selecionado != null) {
                // Preenche os campos com os dados do registro selecionado
                dpData.setValue(selecionado.getData());
                txtConteudo.setText(selecionado.getConteudo());

                // Remove o registro antigo
                controller.removerRegistroDiario(selecionado);
                atualizarLista(listView);
            } else {
                showAlert("Aviso", "Selecione um registro para editar!");
            }
        });

        // Layout melhor organizado
        VBox formLayout = new VBox(10,
                lblData, dpData,
                lblConteudo, txtConteudo,
                btnSalvar
        );
        formLayout.setPadding(new Insets(10));

        VBox listLayout = new VBox(10,
                new Label("Registros de Diário:"),
                listView
        );
        listLayout.setPadding(new Insets(10));

        HBox buttonLayout = new HBox(10, btnEditar, btnRemover, btnVoltar);
        buttonLayout.setPadding(new Insets(10));

        VBox mainLayout = new VBox(10, formLayout, listLayout, buttonLayout);
        mainLayout.setPadding(new Insets(15));

        Scene scene = new Scene(mainLayout, 500, 600);  // Aumentei um pouco o tamanho
        stage.setScene(scene);
        stage.setTitle("Diário - " + usuario.getNome());
        stage.show();
    }

    private void atualizarLista(ListView<Diario> listView){
        // Usa o Controller para obter a lista
        List<Diario> diarios = controller.listarRegistrosDiario();
        listView.getItems().setAll(diarios);
    }

    private void showAlert(String titulo, String msg){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}