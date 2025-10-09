package service;

public class NotificadorBase implements Notificador {
    @Override
    public void enviar(String mensagem) {
        System.out.println("NOTIFICAÇÃO: " + mensagem);
    }
}