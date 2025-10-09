package service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class NotificadorComTimestamp extends NotificadorDecorator {

    public NotificadorComTimestamp(Notificador notificador) {
        super(notificador);
    }

    @Override
    public void enviar(String mensagem) {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
        String mensagemComTimestamp = "[" + timestamp + "] " + mensagem;
        notificadorDecorado.enviar(mensagemComTimestamp);
    }
}