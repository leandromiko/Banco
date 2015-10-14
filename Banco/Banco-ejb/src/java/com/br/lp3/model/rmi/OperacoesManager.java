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
 * @author Leandro Meneguzzi 3144893-3
 * @author Lucas Gianfrancesco 3147173-0
 * @author Pedro Morelatto 3142463-5
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
    public boolean transferencia(int payerId, int receiverId, double value) {
        Userlp3 payer = loginManager.buscarUsuario(payerId);
        Userlp3 receiver = loginManager.buscarUsuario(receiverId);
        if (value > 0 || value < payer.getSaldo()) {
            payer.setSaldo(payer.getSaldo() - value);
            receiver.setSaldo(receiver.getSaldo() + value);
        }
        try {
            Registry registro = LocateRegistry.getRegistry("localhost", 1099);
            GenericDAO servico = (GenericDAO) registro.lookup("UserDAO");
            servico.update(payer);
            servico.update(receiver);
            return true;
        } catch (RemoteException | NotBoundException ex) {
            Logger.getLogger(LoginManager.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public boolean saque(int id, double value) {
        Userlp3 user = loginManager.buscarUsuario(id);
        if (user != null && user.getSaldo() >= value) {
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
