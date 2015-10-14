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

    private LogWriter() throws IOException {
        formatador = new SimpleDateFormat("dd-MM-yyyy HH-MM-SS");
        arq = new FileWriter("c:\\Temp\\log " + formatador.format(new Date()) + ".txt");
        gravarArq = new PrintWriter(arq);
        formatador = new SimpleDateFormat("dd/MM/yyyy HH:MM:SS");
        gravarArq.printf(formatador.format(new Date()) + "\tLOG\tBANCO\tLP3\n");
        arq.close();
    }

    public static synchronized LogWriter getInstance() throws IOException {
        if (instancia == null) {
            instancia = new LogWriter();
        }
        return instancia;
    }

    public void logWriter() throws IOException {
        gravarArq.printf("+--Resultado--+%n");
        for (int i = 1; i <= 10; i++) {
            gravarArq.printf(",mlllh");
        }
        gravarArq.printf("+-------------+%n");
        arq.close();
    }

    public void logWriter(Userlp3 user) throws IOException {
    }
}
