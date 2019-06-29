package util;

public class ValidacaoEmail {
    /*método para validar o email*/
    public static boolean validaEmail(String email) {
        String nome = null;
        String dominio = null;
        String subdominio = null;
        /*verifica se o email está nulo, se estiver*/
        if (email == null || email.isEmpty()){
            /*retorna false*/
            return false;
        }
        /*se estiver ok por enquanto, divide o email no nome do usuário, dominio e subdominio*/
        nome = email.split("@")[0];
        dominio = email.split("@")[1];
        subdominio = dominio.split("\\.")[1];
        dominio = dominio.split("\\.")[0];
        /*verifica primeiramente o nome do usuário, se está no padrões do e-mails*/
        if (nome.matches("\\w+") || (nome.contains(".") || nome.contains("-") || nome.contains("_"))) {
           if(dominio.matches("\\w+") && subdominio.matches("\\w{3}")){
               /*depois de verificar o dominio e subdominio, valida o email retornando true*/
               return true;
           }
        }
        /*senão retorna false*/
        return false;
    }
}
