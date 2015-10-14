package com.br.lp3.model.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 *
 * @author Leandro Meneguzzi 3144893-3
 * @author Lucas Gianfrancesco 3147173-0
 * @author Pedro Morelatto 3142463-5
 */
public interface GenericDAO<T> extends Remote {

    public boolean insert(T t) throws RemoteException;

    public List read() throws RemoteException;

    public boolean update(T t) throws RemoteException;

    public boolean delete(T t) throws RemoteException;

}
