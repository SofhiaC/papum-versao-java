package view;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import controller.MainController;  // Import do Controller

public class MainView {
    private MainController controller;

    public MainView() {
        this.controller = new MainController();
    }

    public void start(Stage stage) {
        // Usa o Controller para verificar login
        if (!controller.isUsuarioLogado()) {
            new LoginView().start(new Stage());
            stage.close();
            return;
        }

        // Cria componentes da UI
        Label lblBoasVindas = new Label("Bem-vindo, " + controller.getNomeUsuario() + "!");
        Label lblEstatisticas = new Label(
                "Você tem " + controller.getQuantidadeTarefas() + " tarefas e " +
                        controller.getQuantidadeDiarios() + " registros no diário."
        );

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
            // Usa o Controller para logout
            controller.logout();
            stage.close();
            new LoginView().start(new Stage());
        });

        // Layout
        VBox layout = new VBox(20, lblBoasVindas, lblEstatisticas, btnTarefas, btnDiario, btnSair);
        layout.setPadding(new Insets(20));

        Scene scene = new Scene(layout, 350, 300);
        stage.setScene(scene);
        stage.setTitle("Tela Principal");
        stage.show();
    }
}