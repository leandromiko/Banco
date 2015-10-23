package com.br.lp3.model.controller;

import com.br.lp3.model.entities.Userlp3;
import com.br.lp3.model.rmi.LoginManagerLocal;
import com.br.lp3.model.rmi.OperacoesManagerLocal;
import com.br.lp3.model.rmi.LogWriterLocal;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Leandro Meneguzzi 3144893-3
 * @author Lucas Gianfrancesco 3147173-0
 * @author Pedro Morelatto 3142463-5
 */
public class FrontController extends HttpServlet {
    
    @EJB
    private LogWriterLocal log;
    @EJB
    private OperacoesManagerLocal operacoesManager;
    @EJB
    private LoginManagerLocal loginManager;
    private String command;
    private Userlp3 user;
    private HttpSession session;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Throwable {
        
        command = request.getParameter("command");
        session = request.getSession();
        
        if (command != null) {
            switch (command) {
                case "login":
                    user = loginManager.authorize(request.getParameter("login"), request.getParameter("senha"));
                    session.setAttribute("user", user);
                    session.setAttribute("saldo", user != null ? operacoesManager.getSaldo(user.getIdUser()) : null);
                    session.setAttribute("isLogged", (user != null));
                    log.logWriter(user);
                    response.sendRedirect(user != null ? "home.jsp" : "index.jsp?login=false");
                    break;
                case "transferencia":
                    session.setAttribute("listaUsuarios", loginManager.buscarUsuarios());
                    response.sendRedirect("transferencia.jsp");
                    break;
                case "realizarTransferencia":
                    Userlp3 giver = (Userlp3) session.getAttribute("user");
                    if (request.getParameter("user").isEmpty()) {
                        response.sendRedirect("transferencia.jsp?user=false");
                    } else {
                        Userlp3 receiver = loginManager.buscarUsuario(Integer.parseInt(request.getParameter("user")));
                        double value = Integer.parseInt(request.getParameter("valor"));
                        
                        if (giver.getSaldo() < value || value <= 0) {
                            log.logWriter(value, receiver, false);
                            response.sendRedirect("transferencia.jsp?transfer=false");
                        } else {
                            operacoesManager.transferencia(giver.getIdUser(), receiver.getIdUser(), value);
                            session.setAttribute("saldo", operacoesManager.getSaldo(giver.getIdUser()));
                            log.logWriter(value, receiver, true);
                            response.sendRedirect("home.jsp?transfer=true");
                        }
                    }
                    break;
                case "saque":
                    response.sendRedirect("saque.jsp");
                    break;
                case "realizarSaque":
                    double valor = Double.parseDouble(request.getParameter("valor"));
                    user = (Userlp3) session.getAttribute("user");
                    if (operacoesManager.saque(user.getIdUser(), valor) && valor > 0) {
                        session.setAttribute("user", user);
                        session.setAttribute("saldo", operacoesManager.getSaldo(user.getIdUser()));
                        log.logWriter(valor, true);
                        response.sendRedirect("home.jsp?saque=true");
                    } else {
                        log.logWriter(valor, false);
                        response.sendRedirect("saque.jsp?saque=false");
                    }
                    break;
                case "logout":
                    session.invalidate();
                    log.logWriter();
                    response.sendRedirect("index.jsp");
                    break;
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Throwable ex) {
            Logger.getLogger(FrontController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Throwable ex) {
            Logger.getLogger(FrontController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
