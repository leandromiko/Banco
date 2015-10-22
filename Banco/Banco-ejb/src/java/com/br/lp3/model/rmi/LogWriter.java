package com.br.lp3.model.rmi;

import com.br.lp3.model.entities.Userlp3;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jms.JMSException;

@Stateless
public class LogWriter implements LogWriterLocal {
    @EJB
    private ProducerLocal producer;
    private static FileWriter arq;
    private SimpleDateFormat formatador;
    private String texto;

    public LogWriter() {
    }

    @Override
    public void logWriter() throws IOException, Throwable {
        formatador = new SimpleDateFormat("dd/MM/yyyy HH:MM:SS");
        texto = formatador.format(new Date()) + "\tLogout Realizado";
        producer.sendMSGtoQueue(arq,texto);
    }

    @Override
    public void logWriter(Userlp3 user) throws IOException {
        formatador = new SimpleDateFormat("dd-MM-yyyy HH-MM-SS");
        arq = new FileWriter("c:\\Temp\\log " + formatador.format(new Date()) + ".txt");
        formatador = new SimpleDateFormat("dd/MM/yyyy HH:MM:SS");
        texto = formatador.format(new Date()) + (user == null ? "\tTentativa de Login Inválidado\t\tUsuário Inválido" : "\tLogin Realizado\t\tUsuário: " + user.getUsername());
        try {
            producer.sendMSGtoQueue(arq, texto);
        } catch (JMSException ex) {
            Logger.getLogger(LogWriter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void logWriter(Double value, Userlp3 receiver, boolean funfo) throws IOException {
        formatador = new SimpleDateFormat("dd/MM/yyyy HH:MM:SS");
        texto = formatador.format(new Date()) + (funfo ? "\tTransferência Realizada\t\tReceptor: " + receiver.getUsername() + "\tQuantia: " + value : "\tTentativa de Transferência Inválida");
        try {
            producer.sendMSGtoQueue(arq,texto);
        } catch (JMSException ex) {
            Logger.getLogger(LogWriter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void logWriter(Double value, boolean funfo) throws IOException {
        formatador = new SimpleDateFormat("dd/MM/yyyy HH:MM:SS");
        texto = formatador.format(new Date()) + (funfo ? "\tSaque Realizado\t\tQuantia: " + value : "\tTentativa de Saque Inválida\t\tQuantia: " + value);
        try {
            producer.sendMSGtoQueue(arq,texto);
        } catch (JMSException ex) {
            Logger.getLogger(LogWriter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
