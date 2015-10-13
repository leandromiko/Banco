/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.lp3.model.rmi;

import com.br.lp3.model.entities.Userlp3;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author 31506976
 */
@Local
public interface LoginManagerLocal {

    public List<Userlp3> buscarUsuarios();
    
    public Userlp3 buscarUsuario(int id);
    
    public Userlp3 authorize(String username, String password);
    
}
