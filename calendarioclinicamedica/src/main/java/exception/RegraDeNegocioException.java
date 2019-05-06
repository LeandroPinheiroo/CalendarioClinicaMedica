/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exception;

/**
 *
 * @author leandro
 */
public class RegraDeNegocioException extends Exception{
    
    public RegraDeNegocioException() {
      super();
    }
    
    public RegraDeNegocioException(String msg) {
      super(msg);
    }
    
}
