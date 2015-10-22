/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.lp3.model.rmi;

import com.br.lp3.model.entities.Userlp3;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

/**
 *
 * @author 31448933
 */
@Stateless
public class Producer implements ProducerLocal {

    @Resource(mappedName = "jms/banco")
    private Queue banco;
    @Resource(mappedName = "jms/bancoFactory")
    private ConnectionFactory bancoFactory;

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    private Message createJMSMessageForjmsBanco(Session session, Object messageData) throws JMSException {
        // TODO create and populate message to send
        TextMessage tm = session.createTextMessage();
        tm.setText(messageData.toString());
        return tm;
    }

    private void sendJMSMessageToBanco(String messageData) throws JMSException {
        Connection connection = null;
        Session session = null;
        try {
            connection = bancoFactory.createConnection();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageProducer messageProducer = session.createProducer(banco);
            messageProducer.send(createJMSMessageForjmsBanco(session, messageData));
        } finally {
            if (session != null) {
                try {
                    session.close();
                } catch (JMSException e) {
                    Logger.getLogger(this.getClass().getName()).log(Level.WARNING, "Cannot close session", e);
                }
            }
            if (connection != null) {
                connection.close();
            }
        }
        //context.createProducer().send(banco, messageData);
    }

    @Override
    public void sendMSGtoQueue(FileWriter arq, String message) throws JMSException {
        PrintWriter gravarArq = new PrintWriter(arq);
        gravarArq.printf(message);
        gravarArq.println();
        gravarArq.flush();
        if(message.contains("Logout"))
            try {
                arq.close();
        } catch (IOException ex) {
            Logger.getLogger(Producer.class.getName()).log(Level.SEVERE, null, ex);
        }
        sendJMSMessageToBanco(message);
    }

}
