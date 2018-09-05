package school.cesar.unit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PessoaTest {
    private CompararPessoas compararPessoas;
    private Pessoa pessoa1;
    private Pessoa pessoa2;

    @BeforeEach
    public void setUp(){
        compararPessoas = new CompararPessoas();
        pessoa1 = new PessoaBuilder()
                .setNome("Izaura")
                .setSobrenome("Dias")
                .setCidade("Recife")
                .setEstado("PE")
                .setNascimento("09-01-2001")
                .setEstadoCivil("solteira")
                .build();
        pessoa2 = new PessoaBuilder()
                .setNome("Mercia")
                .setSobrenome("Dias")
                .setCidade("Recife")
                .setEstado("PE")
                .setNascimento("29-02-2000")
                .setEstadoCivil("casada")
                .build();
    }


    @Test
    public void aniversarioTrue(){
        Assertions.assertTrue(compararPessoas.isAniversario(pessoa1));

    }

    @Test
    public void aniversarioFalse(){

    }

    @Test
    public void aniversarioBissexto_AnoAtualNaoBissexto(){

    }

    @Test
    public void aniversarioBissexto_AnoAtualBissexto(){

    }

    @Test
    public void aniversarioNaoBissexto_AnoAtualBissexto(){

    }

    @Test
    public void aniversarioNaoBissexto_AnoAtualNaoBissexto(){

    }


}
