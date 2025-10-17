package view;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import model.Diario;
import model.ListagemDiario;
import model.Sessao;
import model.Usuario;

import java.time.LocalDate;
import java.util.List;

public class DiarioView {
    private Usuario usuario;
    private ListagemDiario listagem;

    public DiarioView(){
        this.usuario = Sessao.getInstance().getUsuario();
        if (this.usuario == null) {
            throw new IllegalStateException("Nenhum usuário logado para a DiarioView.");
        }
        this.listagem = new ListagemDiario(usuario);
    }

    public void start(Stage stage){
        Label lblData = new Label("Data:");
        DatePicker dpData = new DatePicker(LocalDate.now());

        Label lblConteudo = new Label("Como foi seu dia?");
        TextArea txtConteudo = new TextArea();
        txtConteudo.setPromptText("Escreva aqui seu registro diário...");

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

            Diario diario = new Diario(data, conteudo);
            usuario.adicionarDiario(diario);
            atualizarLista(listView);
            txtConteudo.clear();
        });

        Button btnRemover = new Button("Remover Selecionado");
        btnRemover.setOnAction(e -> {
            Diario selecionado = listView.getSelectionModel().getSelectedItem();
            if (selecionado != null) {
                usuario.getDiarios().remove(selecionado);
                atualizarLista(listView);
            }
        });

        Button btnVoltar = new Button("Voltar");
        btnVoltar.setOnAction(e -> {
            stage.close();
            new MainView().start(new Stage());
        });

        VBox layout = new VBox(10,
                lblData, dpData,
                lblConteudo, txtConteudo,
                btnSalvar,
                new Label("Registros de Diário:"),
                listView,
                new HBox(10, btnRemover, btnVoltar)
        );
        layout.setPadding(new Insets(15));

        Scene scene = new Scene(layout, 450, 500);
        stage.setScene(scene);
        stage.setTitle("Diário - " + usuario.getNome());
        stage.show();
    }

    private void atualizarLista(ListView<Diario> listView){
        List<Diario> diarios = listagem.listar();
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
