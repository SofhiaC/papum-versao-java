package service;

public abstract class NotificadorDecorator implements Notificador {
    protected Notificador notificadorDecorado;

    public NotificadorDecorator(Notificador notificador) {
        this.notificadorDecorado = notificador;
    }

    @Override
    public void enviar(String mensagem) {
        notificadorDecorado.enviar(mensagem);
    }
}