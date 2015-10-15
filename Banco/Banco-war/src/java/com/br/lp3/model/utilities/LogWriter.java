package com.br.lp3.model.utilities;

import com.br.lp3.model.entities.Userlp3;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LogWriter {

    private static LogWriter instancia;
    private static FileWriter arq;
    private static PrintWriter gravarArq;
    private SimpleDateFormat formatador;
    private String texto;

    private LogWriter() throws IOException {
        formatador = new SimpleDateFormat("dd-MM-yyyy HH-MM-SS");
        arq = new FileWriter("c:\\Temp\\log " + formatador.format(new Date()) + ".txt");
        gravarArq = new PrintWriter(arq);
        formatador = new SimpleDateFormat("dd/MM/yyyy HH:MM:SS");
        gravarArq.printf(formatador.format(new Date()) + "\tLOG\tBANCO\tLP3\n");
        gravarArq.println();
        gravarArq.flush();
    }

    public static synchronized LogWriter getInstance() throws IOException {
        if (instancia == null) {
            instancia = new LogWriter();
        }
        return instancia;
    }

    public void logWriter() throws IOException, Throwable {
        gravarArq.printf(formatador.format(new Date()) + "\tLogout Realizado");
        arq.close();
        instancia = null;
    }

    public void logWriter(Userlp3 user) throws IOException {
        texto=(user == null? "\tTentativa de Login Invalidado\t\tUsuario Invalido":"\tLogin Realizado\t\tUsuario: "+user.getUsername());
        gravarArq.printf(formatador.format(new Date()) + texto);
        gravarArq.println();
        gravarArq.flush();
    }

    public void logWriter(Double value, Userlp3 receiver, boolean funfo) throws IOException {
        texto = (funfo?"\tTransferencia Realizada\tReceptor: " + receiver.getUsername() + "\tQuantia: " + value:"\tTentativa de Transfência inválida");
        gravarArq.printf(formatador.format(new Date()) + texto);
        gravarArq.println();
        gravarArq.flush();
    }

    public void logWriter(Double value, boolean funfo) throws IOException {
        texto = (funfo? "\tSaque Realizado\tQuantia: " + value:"\tTentativa de Saque Invalido\tQuantia: " + value);
        gravarArq.printf(formatador.format(new Date()) + texto);
        gravarArq.println();
        gravarArq.flush();
    }
}