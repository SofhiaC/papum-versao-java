package view;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import model.Usuario;
import model.Sessao;

public class MainView {

    public MainView() {
    }

    public void start(Stage stage) {
        // Obter o usuário da Sessao
        Usuario usuario = Sessao.getInstance().getUsuario();

        if (usuario == null) {
            new LoginView().start(new Stage());
            return;
        }

        Button btnTarefas = new Button("Minhas Tarefas");
        btnTarefas.setOnAction(e -> {
            stage.close();
            new TarefaView().start(new Stage());
        });

        Button btnDiario = new Button("Meu Diário");
        btnDiario.setOnAction(e -> {
            stage.close();
            new DiarioView().start(new Stage());
        });

        Button btnSair = new Button("Sair");
        btnSair.setOnAction(e -> {
            // Limpa o usuário da Sessao (Logout)
            Sessao.getInstance().logout();
            stage.close();
            new LoginView().start(new Stage());
        });

        VBox layout = new VBox(20, btnTarefas, btnDiario, btnSair);
        layout.setPadding(new Insets(20));

        Scene scene = new Scene(layout, 300, 250);
        stage.setScene(scene);
        stage.setTitle("Tela Principal - " + usuario.getNome());
        stage.show();
    }
}