/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.lp3.model.rmi;

import com.br.lp3.model.entities.Userlp3;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;

/**
 *
 * @author 31506976
 */
@Stateless
public class LoginManager implements LoginManagerLocal {

    @Override
    public List<Userlp3> buscarUsuarios() {

        List<Userlp3> lista = new ArrayList<>();
        try {
            Registry registro = LocateRegistry.getRegistry("localhost", 1099);
            GenericDAO servico = (GenericDAO) registro.lookup("UserDAO");
            lista = servico.read();
        } catch (RemoteException | NotBoundException ex) {
            Logger.getLogger(LoginManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    @Override
    public Userlp3 authorize(String username, String password) {
        List<Userlp3> lista = buscarUsuarios();
        for (Userlp3 user : lista) {
            if(user.getUsername().equals(username) && user.getPassword().equals(password))
                return user;
        }
        return null;
    }

    @Override
    public Userlp3 buscarUsuario(int id) {
        for (Userlp3 user : buscarUsuarios()) {
            if(user.getIdUser().equals(id))
                return user;
        }
        return null;
    }
}
