package bancoDB;

import com.br.lp3.model.rmi.UserDAO;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 *
 * @author Leandro Meneguzzi 3144893-3
 * @author Lucas Gianfrancesco 3147173-0
 * @author Pedro Morelatto 3142463-5
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
