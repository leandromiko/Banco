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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author 31424635
 */
@Stateless
public class OperacoesManager implements OperacoesManagerLocal {

    @EJB
    private LoginManagerLocal loginManager;

    @Override
    public double getSaldo(int id) {
        Userlp3 user = loginManager.buscarUsuario(id);
        return (user != null) ? user.getSaldo() : 0;
    }

    @Override
    public boolean transferencia(int payerId, int reciverId, double value) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean saque(int id, double value) {
        Userlp3 user = loginManager.buscarUsuario(id);
        if (user != null && user.getSaldo() > value) {
            user.setSaldo(user.getSaldo() - value);
            try {
                Registry registro = LocateRegistry.getRegistry("localhost", 1099);
                GenericDAO servico = (GenericDAO) registro.lookup("UserDAO");
                servico.update(user);
                return true;
            } catch (RemoteException | NotBoundException ex) {
                Logger.getLogger(LoginManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

}
