/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.lp3.model.controller;

import com.br.lp3.model.entities.Userlp3;
import com.br.lp3.model.rmi.LoginManagerLocal;
import com.br.lp3.model.rmi.OperacoesManagerLocal;
import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author 31506976
 */
public class FrontController extends HttpServlet {

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
            throws ServletException, IOException {

        command = request.getParameter("command");
        session = request.getSession();

        if (command != null) {
            switch (command) {
                case "login":
                    user = loginManager.authorize(request.getParameter("login"), request.getParameter("senha"));
                    session.setAttribute("user", user);
                    session.setAttribute("isLogged", (user != null));
                    response.sendRedirect(user != null ? "home.jsp" : "index.jsp?login=false");
                    break;
                case "saldo":
                    response.sendRedirect("saldo.jsp");
                    break;
                case "transferencia":
                    session.setAttribute("listaUsuarios", loginManager.buscarUsuarios());
                    response.sendRedirect("transferencia.jsp");
                    break;
                case "saque":
                    response.sendRedirect("saque.jsp");
                    break;
                case "realizarSaque":
                    double valor = Double.parseDouble(request.getParameter("valor"));
                    user = (Userlp3) session.getAttribute("user");
                    if (operacoesManager.saque(user.getIdUser(), valor)) {
                        user.setSaldo(operacoesManager.getSaldo(user.getIdUser()));
                        session.setAttribute("user", user);
                        response.sendRedirect("home.jsp");
                    } else {
                        response.sendRedirect("saque.jsp");
                    }
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
        processRequest(request, response);
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
        processRequest(request, response);
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
