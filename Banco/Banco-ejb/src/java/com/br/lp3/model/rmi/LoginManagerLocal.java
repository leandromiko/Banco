package com.br.lp3.model.rmi;

import com.br.lp3.model.entities.Userlp3;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Leandro Meneguzzi 3144893-3
 * @author Lucas Gianfrancesco 3147173-0
 * @author Pedro Morelatto 3142463-5
 */
@Local
public interface LoginManagerLocal {

    public List<Userlp3> buscarUsuarios();

    public Userlp3 buscarUsuario(int id);

    public Userlp3 authorize(String username, String password);

}
