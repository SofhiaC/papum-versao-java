package view;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import model.Usuario;

public class MainView {

    private Usuario usuario;

    public MainView(Usuario usuario) {
        this.usuario = usuario;
    }

    public void start(Stage stage) {
        Button btnTarefas = new Button("Minhas Tarefas");
        btnTarefas.setOnAction(e -> new TarefaView(usuario).start(new Stage()));

        Button btnDiario = new Button("Meu Diário");
        btnDiario.setOnAction(e -> new DiarioView(usuario).start(new Stage()));

        Button btnSair = new Button("Sair");
        btnSair.setOnAction(e -> {
            stage.close();
            new LoginView().start(new Stage());
        });

        VBox layout = new VBox(20, btnTarefas, btnDiario, btnSair);
        layout.setPadding(new Insets(20));

        Scene scene = new Scene(layout, 300, 250);
        stage.setScene(scene);
        stage.setTitle("Tela Principal");
        stage.show();
    }
}
