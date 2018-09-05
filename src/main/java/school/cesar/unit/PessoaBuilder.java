package school.cesar.unit;

public class PessoaBuilder {
    private String nome = "";
    private String sobrenome = "";
    private String nascimento = "";
    private String estadoCivil = "";
    private String cidade = "";
    private String estado = "";

    public PessoaBuilder(){}

    public PessoaBuilder setNome (String nome){
        this.nome = nome;
        return this;
    }

    public PessoaBuilder setSobrenome (String sobrenome){
        this.sobrenome = sobrenome;
        return this;
    }

    public PessoaBuilder setNascimento (String nascimento){
        this.nascimento = nascimento;
        return this;
    }

    public PessoaBuilder setEstadoCivil (String estadoCivil){
        this.estadoCivil = estadoCivil;
        return this;
    }

    public PessoaBuilder setCidade (String cidade){
        this.cidade = cidade;
        return this;
    }

    public PessoaBuilder setEstado (String estado){
        this.estado = estado;
        return this;
    }

    public Pessoa build(){
        return new Pessoa(nome, sobrenome, nascimento, estadoCivil, cidade, estado);
    }

}
