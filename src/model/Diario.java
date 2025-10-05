package model;

import java.time.LocalDate;

public class Diario {
    private LocalDate data;
    private String conteudo;

    public Diario(LocalDate data, String conteudo){
        this.data = data;
        this.conteudo = conteudo;
    }

    public LocalDate getData(){
        return data;
    }

    public String getConteudo(){
        return conteudo;
    }

    public void setConteudo(String conteudo){
        this.conteudo = conteudo;
    }

    @Override
    public String toString(){
        return data + " - " + conteudo;
    }
}
