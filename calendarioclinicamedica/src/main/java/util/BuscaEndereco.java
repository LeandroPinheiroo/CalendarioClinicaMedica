package util;

import java.io.IOException;
import java.net.SocketTimeoutException;

import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class BuscaEndereco {
    /*método para buscar uma rua pelo cep*/
    public static String getStreet(String CEP) throws IOException {
        try {
            /*recebe as informações http via o site dos correios*/
            Document doc = Jsoup.connect("http://www.qualocep.com/busca-cep/" + CEP).timeout(120000).get();
            /*busca o item rua*/
            Elements urlSearch = doc.select("span[itemprop=streetAddress]");
            /*varre as ruas de acordo com o cep*/
            for (Element urlAdress : urlSearch) {
                /*e retorna se encontrar*/
                return urlAdress.text();
            }
        } catch (HttpStatusException w) {
            
        }
        /*senão encontrar retorna null*/
        return null;
    }

    /*método para buscar o bairro pelo cep*/
    public static String getNeighborhood(String CEP) throws IOException {
        try {
            /*recebe as informações http via o site dos correios*/
            Document doc = Jsoup.connect("http://www.qualocep.com/busca-cep/" + CEP).timeout(120000).get();
            /*busca o item bairro*/
            Elements urlPesquisa = doc.select("td:gt(1)");
            for (Element urlBairro : urlPesquisa) {
                /*e retorna se encontrar*/
                return urlBairro.text();
            }
        } catch (SocketTimeoutException e) {

        } catch (HttpStatusException w) {

        }
        /*senão encontrar retorna null*/
        return null;
    }

    /*método para buscar a cidade pelo cep*/
    public static String getCity(String CEP) throws IOException {
        try{
            /*recebe as informações http via o site dos correios*/
            Document doc = Jsoup.connect("http://www.qualocep.com/busca-cep/"+CEP).timeout(120000).get();
            /*busca o item cidade*/
            Elements urlPesquisa = doc.select("span[itemprop=addressLocality]");
            for (Element urlCidade : urlPesquisa) {
                /*e retorna se encontrar*/
                return urlCidade.text();
            }
        } catch (SocketTimeoutException e) {

        } catch (HttpStatusException w) {

        }
        /*senão encontrar retorna null*/
        return null;
    }
    /*método para buscar a unidade federativa do estado*/
    public static String getUF(String CEP) throws IOException {
        try{
            /*recebe as informações http via o site dos correios*/
            Document doc = Jsoup.connect("http://www.qualocep.com/busca-cep/"+CEP).timeout(120000).get();
            /*busca o item cidade*/
            Elements urlPesquisa = doc.select("span[itemprop=addressRegion]");
            for (Element urlUF : urlPesquisa) {
                /*e retorna se encontrar*/
                return urlUF.text();
            }
        } catch (SocketTimeoutException e) {

        } catch (HttpStatusException w) {

        }
        /*senão encontrar retorna null*/
        return null;
    }

}
