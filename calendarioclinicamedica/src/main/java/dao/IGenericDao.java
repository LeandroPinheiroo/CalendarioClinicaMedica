/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author devai
 */
public interface IGenericDao<T,ID extends Serializable> {
    
    public void salvar(T entidade);
    public boolean remover(ID id);
    public T buscarPorID(ID id);
    public List<T> buscarTodas();
    
}




