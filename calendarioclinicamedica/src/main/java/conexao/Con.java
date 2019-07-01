/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexao;

import java.sql.DriverManager;

/**
 *
 * @author weth767
 */
public class Con {
    private static java.sql.Connection con;
    
    private Con() throws Exception{
        try{
            String driver = "com.mysql.jdbc.Driver";
            String url = "jdbc:mysql://localhost:3306/clinica";
            String usuario = "root";
            String senha = "MOwo7898#@";
            
            Class.forName(driver);
            con = DriverManager.getConnection(url, usuario, senha);
            con.setAutoCommit(true);
        }catch(Exception e){
            throw e;
        }
    }
    
     public static java.sql.Connection getInstance() throws Exception{
        if(con == null){
            new Con();
        }
        return con;
    }
}
