package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Notificador {

    public void enviar(String mensagem) {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
        System.out.println("📢 NOTIFICAÇÃO: [" + timestamp + "] " + mensagem);
    }
}