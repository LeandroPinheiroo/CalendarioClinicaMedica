package util;

public class ConversorData {
    /*método para converter data do formato americano para o formato brasileiro*/
    public static String convertDateUSAtoBR(String date){
        /*quebra a data em pedaços de acordo com o traço*/
        String dateVector[] = date.split("-");
        /*pega os valores de data separados*/
        String year = dateVector[0];
        String mouth = dateVector[1];
        String day = dateVector[2];
        /*agora remonta a data, mas já no formato br e a retorna*/
        return day + "/" + mouth + "/" + year;
    }

    /*método para converter data no formato brasileiro para o formato americano*/
    public static String convertDateBRtoUSA(String date){
        /*quebra a data em pedaços de acordo com o barra*/
        String dateVector[] = date.split("/");
        /*pega os valores de data separados*/
        String year = dateVector[2];
        String mouth = dateVector[1];
        String day = dateVector[0];
        /*agora remonta a data, mas já no formato br e a retorna*/
        return year + "-" + mouth + "-" + day;
    }
}
