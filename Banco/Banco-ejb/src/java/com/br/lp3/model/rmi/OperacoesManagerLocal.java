package com.br.lp3.model.rmi;

import javax.ejb.Local;

/**
 *
 * @author Leandro Meneguzzi 3144893-3
 * @author Lucas Gianfrancesco 3147173-0
 * @author Pedro Morelatto 3142463-5
 */
@Local
public interface OperacoesManagerLocal {

    public double getSaldo(int id);

    public boolean transferencia(int payerId, int reciverId, double value);

    public boolean saque(int id, double value);

}
