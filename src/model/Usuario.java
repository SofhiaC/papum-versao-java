package model;

import java.util.ArrayList;
import java.util.List;

public class Usuario {

    /** id do usuário*/
    private int id;
    private String nome;
    private String senha;
    private List<Tarefa> tarefas;
    private List<Diario> diarios;

    /** construtor do usuário */
    public Usuario(int id, String nome, String senha) {
        this.id = id;
        this.nome = nome;
        this.senha = senha;
        this.tarefas = new ArrayList<>();
        this.diarios = new ArrayList<>();
    }

    /** getters */
    public int getId(){
        return id;
    }

    public String getNome(){
        return nome;
    }

    public String getSenha(){
        return senha;
    }

    public List<Tarefa> getTarefas(){
        return tarefas;
    }

    public List<Diario> getDiarios(){
        return diarios;
    }

    /**
     * tendo em vista que a gente vai armazenar os dados dos usuários em memória,
     * não precisa de setters
     * porque não vai ser necessário definir por um formulário os dados
     * se tiver dúvida, pesquise sobre armazenamento de dados em memória
     */

    @Override
    public String toString(){
        return "Usuario{id" + id + ", nome= '" + nome + "'}";
    }

    /**
     * adiciona a tarefa no arraylist e é o mesmo pro diário
     */
    public void adicionarTarefa(Tarefa tarefa){
        tarefas.add(tarefa);
    }

    public void adicionarDiario(Diario diario){
        diarios.add(diario);
    }

}
