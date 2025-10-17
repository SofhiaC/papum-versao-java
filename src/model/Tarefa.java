package model;

import java.time.LocalDate;

public class Tarefa {
    private String titulo;
    private String descricao;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private boolean concluida;

    public Tarefa(String titulo, String descricao, LocalDate dataInicio, LocalDate dataFim){
        this.titulo = titulo;
        this.descricao = descricao;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.concluida = false;
    }

    //getters
    public String getTitulo(){
        return titulo;
    }

    public String getDescricao(){
        return descricao;
    }

    public LocalDate getDataInicio(){
        return dataInicio;
    }

    public LocalDate getDataFim(){
        return dataFim;
    }

    public boolean isConcluida(){
        return concluida;
    }

    //setters
    public void setTitulo(String titulo){
        this.titulo = titulo;
    }

    public void setDescricao(String descricao){
        this.descricao = descricao;
    }

    public void setDataInicio(LocalDate dataInicio){
        this.dataInicio = dataInicio;
    }

    public void setDataFim(LocalDate dataFim){
        this.dataFim = dataFim;
    }

    public void setConcluida(boolean concluida){
        this.concluida = concluida;
    }

    @Override
    public String toString(){
        return (concluida ? "[✔] " : "[ ] ") + titulo + "(até " + dataFim + ")";
    }

}
