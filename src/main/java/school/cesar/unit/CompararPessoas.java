package school.cesar.unit;

import java.util.Calendar;
import java.util.Date;

public class CompararPessoas {
    public boolean isAniversario(Pessoa pessoa01){
        String currentDay = String.valueOf(Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        String currentMonth = String.valueOf(Calendar.getInstance().get(Calendar.MONTH));
        String currentDate = currentDay + "-" + currentMonth + "-";

        String pessoaBirthdate = pessoa01.getNascimento().substring( 0, 4);

        if(pessoaBirthdate.contains("29-02")){
            LeapYear leapYear = new LeapYear();
            if(!leapYear.isLeap()){
                pessoaBirthdate = "01-03";
            }
        }

        if(pessoaBirthdate.contains(currentDate)){
            return true;
        } else {
            return false;
        }
    }

    public boolean isFamilia(Pessoa pessoa01, Pessoa pessoa02){
        if(!pessoa01.getNascimento().equalsIgnoreCase(pessoa02.getNome())&&
                pessoa01.getSobrenome().equalsIgnoreCase(pessoa02.getSobrenome())&&
                pessoa01.getCidade().equalsIgnoreCase(pessoa02.getCidade())&&
                pessoa01.getEstado().equalsIgnoreCase(pessoa02.getEstado())){
            return true;
        } else {
            return false;
        }
    }

    public boolean isSameInformation(Pessoa pessoa01, Pessoa pessoa02){
        if(pessoa01.getNome().equalsIgnoreCase(pessoa02.getNome())){
            if(pessoa01.getSobrenome().equalsIgnoreCase(pessoa02.getSobrenome())){
                if(pessoa01.getNascimento().equalsIgnoreCase(pessoa02.getNascimento())){
                    if(pessoa01.getEstadoCivil().equalsIgnoreCase(pessoa02.getEstadoCivil())){
                        if(pessoa01.getCidade().equalsIgnoreCase(pessoa02.getCidade())){
                            if(pessoa01.getEstado().equalsIgnoreCase(pessoa02.getEstado())){
                                return true;
                            } else return false;
                        } else return false;
                    } else return false;
                } else return false;
            } else return false;
        } else return false;
    }
}
