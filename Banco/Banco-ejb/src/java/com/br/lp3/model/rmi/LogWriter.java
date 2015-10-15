package com.br.lp3.model.rmi;

import com.br.lp3.model.entities.Userlp3;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.ejb.Stateless;

@Stateless
public class LogWriter implements LogWriterLocal{

    private static FileWriter arq;
    private static PrintWriter gravarArq;
    private SimpleDateFormat formatador;
    private String texto;

    public LogWriter() {
    }

    @Override
    public void logWriter() throws IOException, Throwable {
        gravarArq.printf(formatador.format(new Date()) + "\tLogout Realizado");
        arq.close();
    }

    @Override
    public void logWriter(Userlp3 user) throws IOException {
        formatador = new SimpleDateFormat("dd-MM-yyyy HH-MM-SS");
        arq = new FileWriter("c:\\Temp\\log " + formatador.format(new Date()) +" "+ (user!=null?user.getUsername():"")+".txt");
        gravarArq = new PrintWriter(arq);
        formatador = new SimpleDateFormat("dd/MM/yyyy HH:MM:SS");
        texto=(user == null? "\tTentativa de Login Invalidado\t\tUsuario Invalido":"\tLogin Realizado\t\tUsuario: "+user.getUsername());
        gravarArq.printf(formatador.format(new Date()) + texto);
        gravarArq.println();
        gravarArq.flush();
    }

    @Override
    public void logWriter(Double value, Userlp3 receiver, boolean funfo) throws IOException {
        texto = (funfo?"\tTransferencia Realizada\t\tReceptor: " + receiver.getUsername() + "\tQuantia: " + value:"\t\tTentativa de Transfência inválida");
        gravarArq.printf(formatador.format(new Date()) + texto);
        gravarArq.println();
        gravarArq.flush();
    }

    @Override
    public void logWriter(Double value, boolean funfo) throws IOException {
        texto = (funfo? "\tSaque Realizado\t\tQuantia: " + value:"\tTentativa de Saque Invalido\t\tQuantia: " + value);
        gravarArq.printf(formatador.format(new Date()) + texto);
        gravarArq.println();
        gravarArq.flush();
    }
}