package util;

public class ValidacaoCPF {
    public static boolean validaCPF(String cpf) {
        /*retorna false se a string estiver nula*/
        if(cpf == null){
            return false;
        }
        /*quebra a string deixando apenas numeros*/
        cpf = cpf.replaceAll("[\\.-]", "").trim();
        if(cpf.equals("")){
            return false;
        }
        /*verifica os números que resultam na contagem correta, mas não são validos*/
        if ((cpf.equals("00000000000")) || (cpf.equals("11111111111")) || (cpf.equals("22222222222")) || (cpf.equals("33333333333"))
                || (cpf.equals("44444444444")) || (cpf.equals("55555555555")) || (cpf.equals("66666666666")) || (cpf.equals("77777777777"))
                || (cpf.equals("88888888888")) || (cpf.equals("99999999999"))) {
            /*Como numeros inteiramente identicos sao validos eles devem ser capturados de forma pratica
             e então retorna false*/
            return false;
        } else {
            /*senão foi reprovado inicialmente verifica os digitos para validar o CPF*/
            String s1, s2, s3, s4, s5, s6, s7, s8, s9, checker = "";
            int n1, n2, n3, n4, n5, n6, n7, n8, n9, verificator1, verificator2;
            /*divide o cpf em vários números*/
            s1 = cpf.substring(0, 1);
            n1 = Integer.parseInt(s1);
            s2 = cpf.substring(1, 2);
            n2 = Integer.parseInt(s2);
            s3 = cpf.substring(2, 3);
            n3 = Integer.parseInt(s3);

            s4 = cpf.substring(3, 4);
            n4 = Integer.parseInt(s4);
            s5 = cpf.substring(4, 5);
            n5 = Integer.parseInt(s5);
            s6 = cpf.substring(5, 6);
            n6 = Integer.parseInt(s6);

            s7 = cpf.substring(6, 7);
            n7 = Integer.parseInt(s7);
            s8 = cpf.substring(7, 8);
            n8 = Integer.parseInt(s8);
            s9 = cpf.substring(8, 9);
            n9 = Integer.parseInt(s9);
            /*faz a conta com o primeiro digito verificador*/
            verificator1 = (n1 * 10 + n2 * 9 + n3 * 8 + n4 * 7 + n5 * 6 + n6 * 5 + n7 * 4 + n8 * 3 + n9 * 2);
            /*verifica a dividibilidade do primeiro verificar por 11, que é um valor chave de verificação*/
            if ((verificator1 % 11) < 2) {
                verificator1 = 0;
            } else {
                verificator1 = 11 - (verificator1 % 11);
            }
            /*faz a conta com o segundo verificador*/
            verificator2 = (n1 * 11 + n2 * 10 + n3 * 9 + n4 * 8 + n5 * 7 + n6 * 6 + n7 * 5 + n8 * 4 + n9 * 3 + verificator1 * 2);
            /*verifica também a divisibilidade dele*/
            if ((verificator2 % 11) < 2) {
                verificator2 = 0;
            } else {
                verificator2 = 11 - (verificator2 % 11);
            }
            /*faz a concatenação desses valores*/
            checker = (s1 + s2 + s3 + s4 + s5 + s6 + s7 + s8 + s9 + verificator1 + verificator2);
            /*caso o cpf seja valido, retorna true*/
            if (checker.equals(cpf)) {
                return true;
            }/*senão for, retorna false*/
            else {
                return false;
            }
        }
    }
}
