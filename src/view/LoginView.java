package view;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import model.Usuario;
import model.Sessao;
import service.LoginService;

public class LoginView extends Application{

    private LoginService loginService = LoginService.getInstance();

    @Override
    public void start(Stage primaryStage){
        primaryStage.setTitle("Pá-pum!");

        //campos de entrada
        Label nomeLabel = new Label("Nome:");
        TextField nomeTextField = new TextField();
        Label senhaLabel = new Label("Senha:");
        PasswordField senhaField = new PasswordField();
        //menagem de erro
        Label mensagemLabel = new Label();
        mensagemLabel.setStyle("-fx-text-fill: red;");
        //botao
        Button loginButton = new Button("Entrar");
        loginButton.setOnAction(e -> {
            String nome = nomeTextField.getText();
            String senha = senhaField.getText();

            Usuario usuarioLogado = loginService.login(nome, senha);
            if (usuarioLogado != null){
                // Armazena o usuário logado na Sessao
                Sessao.getInstance().setUsuario(usuarioLogado);
                mensagemLabel.setText("");
                abrirMainView(primaryStage);
            } else {
                mensagemLabel.setText("Login inválido! Tente novamente.");
            }
        });

        //estilização etc etc etc
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setVgap(10);
        grid.setHgap(10);

        grid.add(nomeLabel, 0, 0);
        grid.add(nomeTextField, 1, 0);
        grid.add(senhaLabel, 0, 1);
        grid.add(senhaField, 1, 1);
        grid.add(loginButton, 1, 2);
        grid.add(mensagemLabel, 1, 3);

        Scene scene = new Scene(grid, 350, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void abrirMainView(Stage primaryStage) {
        primaryStage.close();
        new MainView().start(new Stage());
    }
}
