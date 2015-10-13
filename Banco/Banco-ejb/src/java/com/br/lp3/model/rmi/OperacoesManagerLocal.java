/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.lp3.model.rmi;

import javax.ejb.Local;

/**
 *
 * @author 31506976
 */
@Local
public interface OperacoesManagerLocal {

    public double getSaldo(int id);

    public boolean transferencia(int payerId,int reciverId, double value);
    
    public boolean saque(int id, double value);
        
}
