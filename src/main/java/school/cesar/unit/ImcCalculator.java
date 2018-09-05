package school.cesar.unit;

public class ImcCalculator {

    public static final String DESNUTRICAO = "desnutrição";
    public static final String ABAIXO_DO_PESO = "abaixo do peso";
    public static final String NORMAL = "normal";
    public static final String SOBREPESO = "sobrepeso";
    public static final String OBESIDADE = "obesidade";
    public static final String OBESIDADE_MORBIDA = "obesidade morbida";


    public String calc(double altura, double peso){
        double imc = peso / (altura * altura);

        if (imc < 17) {
          return DESNUTRICAO;
        } else if(imc < 18.5){
            return ABAIXO_DO_PESO;
        } else if (imc < 25){
            return NORMAL;
        } else if (imc < 30){
            return SOBREPESO;
        } else if (imc < 40){
            return OBESIDADE;
        } else{
            return OBESIDADE_MORBIDA;
        }
    }
}
