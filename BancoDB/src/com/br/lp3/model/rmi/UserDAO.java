package com.br.lp3.model.rmi;

import com.br.lp3.model.entities.Userlp3;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Leandro Meneguzzi 3144893-3
 * @author Lucas Gianfrancesco 3147173-0
 * @author Pedro Morelatto 3142463-5
 */
public class UserDAO extends UnicastRemoteObject implements GenericDAO<Userlp3> {

    public UserDAO() throws RemoteException {

    }

    @Override
    public boolean insert(Userlp3 t) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List read() throws RemoteException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("BancoDBPU");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        List<Userlp3> lista = em.createNamedQuery("Userlp3.findAll").getResultList();

        em.getTransaction().commit();
        em.close();

        return lista;
    }

    @Override
    public boolean update(Userlp3 t) throws RemoteException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("BancoDBPU");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        Userlp3 user = em.find(Userlp3.class, t.getIdUser());
        user.setPassword(t.getPassword());
        user.setSaldo(t.getSaldo());
        user.setUsername(t.getUsername());
        em.getTransaction().commit();
        em.close();
        return true;
    }

    @Override
    public boolean delete(Userlp3 t) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
