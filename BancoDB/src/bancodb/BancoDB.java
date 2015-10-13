/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bancoDB;

import com.br.lp3.model.rmi.UserDAO;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 *
 * @author 31506976
 */
public class BancoDB {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            Registry registro = LocateRegistry.createRegistry(1099);
            registro.rebind("UserDAO", new UserDAO());
            System.out.println("Serviço registrado com sucesso!");
        } catch (RemoteException ex) {
        }
    }
}
