package util;
/*classe de verificação de força da senha*/
public class ValidacaoSenha {
    /*método estatico para verifiica se a senha é forte e retorna a mensagem sobre o que está faltando para ser*/
    public static String passwordIsStrong(String password){
        /*transforma a senha em uma array de char*/
        char pass[] = password.toCharArray();
        /*verifica primeiro se o tamanho é pelo menos de tamanho 8*/
        if(password.length() < 8){
            /*senão for, ja retorna o erro*/
            return "A senha deve possuir no mínimo 8 caracteres";
        }
        /*duas variaveis de controle para verificiar se há numero e letra na senha*/
        boolean hasUpperChar = false;
        boolean hasDigitChar = false;
        /*verifica sobre o array de char se há algum que é maisculo e se há algum que é digito*/
        for(int i = 0; i < pass.length; i++){
            if(Character.isUpperCase(pass[i])){
                hasUpperChar = true;
            }
            if(Character.isDigit(pass[i])){
                hasDigitChar = true;
            }
        }
        /*agora faz a verificação*/
        if(hasUpperChar == false){
            return "A senha deve conter ao menos um caractere maísculo";
        }
        if(hasDigitChar == false){
            return "A senha deve conter ao menos um digito número";
        }
        return null;
    }
}
