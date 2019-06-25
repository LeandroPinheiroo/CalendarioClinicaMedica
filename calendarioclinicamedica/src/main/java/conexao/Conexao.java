package conexao;

import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class Conexao {

    private static EntityManagerFactory emFactory =
            Persistence.createEntityManagerFactory("Clinica");
    private static EntityManager em;

    private Conexao() {
    }

    public static EntityManager getConexao(){
        if(em == null){
            em = emFactory.createEntityManager();
        }
        return em;
    }
}
