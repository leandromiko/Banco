/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.lp3.model.rmi;

import java.io.FileWriter;
import javax.ejb.Local;
import javax.jms.JMSException;
import javax.jms.Message;

/**
 *
 * @author 31448933
 */
@Local
public interface ProducerLocal {
    public void sendMSGtoQueue(FileWriter arq, String message) throws JMSException;
}
