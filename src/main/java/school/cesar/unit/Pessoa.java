package school.cesar.unit;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Pessoa {
    private String nome = "";
    private String sobrenome = "";
    private String nascimento = "";
    private String estadoCivil = "";
    private String cidade = "";
    private String estado = "";

    public Pessoa(String nome,String sobrenome,String nascimento,String estadoCivil,String cidade,String estado){
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.nascimento = nascimento;
        this.estadoCivil = estadoCivil;
        this.cidade = cidade;
        this.estado = estado;
    }

    public String getNome(){
        return nome;
    }

    public String getSobrenome(){
        return sobrenome;
    }

    public String getNascimento(){
        return nascimento;
    }

    public String getEstadoCivil(){
        return estadoCivil;
    }
    public String getCidade(){
       return cidade;
    }

    public String getEstado(){
        return estado;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public void setNascimento(String nascimento) {
        this.nascimento = nascimento;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

}