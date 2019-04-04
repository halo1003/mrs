/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import edu.stanford.smi.protege.exception.OntologyLoadException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import reasoner.Reasoner;
import reasoner.ontology;

/**
 *
 * @author QQ
 */
@WebServlet(name = "Check", urlPatterns = {"/Check"})
public class Check extends HttpServlet {

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
            throws ServletException, IOException, OntologyLoadException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            HttpSession session = request.getSession(false);
            String action = request.getParameter("action");
            // Make sure server already connect with database
            Reasoner controlAccount = (Reasoner) session.getAttribute("controlAccount");
            if("login".equals(action)){
                String user_id = request.getParameter("user_id");
//                user_id = "u257c6f34bfdc6153204849499d7a9313841ad590";
                if(controlAccount.login(user_id)){
                    session.setAttribute("controlAccount", controlAccount);
                    session.setAttribute("display", "top_play");
                }
                response.sendRedirect("home.jsp");
            } else if("search_tool".equals(action)) {
                String name = request.getParameter("name");
                session.setAttribute("main_id", name);
                session.setAttribute("display", "action_get_song_from_search");
                response.sendRedirect("home.jsp");
            } else if("logout".equals(action)) {
                controlAccount.logout();
                session.setAttribute("controlAccount", controlAccount);
                session.setAttribute("display", "top_play");
                response.sendRedirect("home.jsp");
            } else if("top_play".equals(action)){
                session.setAttribute("display", "top_play");
                response.sendRedirect("home.jsp");
            } else if("category".equals(action)) {
                session.setAttribute("display", "category");
                response.sendRedirect("home.jsp");
            } else if("artist".equals(action)) {
                session.setAttribute("display", "artist");
                response.sendRedirect("home.jsp");
            } else if("history".equals(action)) {
                session.setAttribute("display", "history");
                response.sendRedirect("home.jsp");
            } else if("mr".equals(action)) {
                session.setAttribute("display", "mr");
                response.sendRedirect("home.jsp");
            } else if("action_get_song_from_tag".equals(action)){
                String tag_id = request.getParameter("tag_id");
                session.setAttribute("display", "action_get_song_from_tag");
                session.setAttribute("main_id", tag_id);
                response.sendRedirect("home.jsp");
            }  else if("action_get_song_from_artist".equals(action)){
                String artist_id = request.getParameter("artist_id");
                session.setAttribute("display", "action_get_song_from_artist");
                session.setAttribute("main_id", artist_id);
                response.sendRedirect("home.jsp");
            } else if("action_listen".equals(action)) {
                String track_id = request.getParameter("track_id");
                controlAccount.increase_listening_times(track_id);
                session.setAttribute("display", "action_listen");
                session.setAttribute("main_id", track_id);
                response.sendRedirect("home.jsp");
            } else if("action_like".equals(action)) {
                String track_id = (String) session.getAttribute("main_id");
                controlAccount.check_like(track_id, "like");
                response.sendRedirect("home.jsp");
            } else if("action_dislike".equals(action)) {
                String track_id = (String) session.getAttribute("main_id");
                controlAccount.check_like(track_id, "dislike");
                response.sendRedirect("home.jsp");
            } else if("register".equals(action)) {
                String user_id = request.getParameter("user_id");
//                user_id = "u257c6f34bfdc6153204849499d7a9313841ad590";
                if(controlAccount.register(user_id)){
                    session.setAttribute("controlAccount", controlAccount);
                    session.setAttribute("display", "top_play");
                }
                response.sendRedirect("home.jsp");
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
        } catch (OntologyLoadException ex) {
            Logger.getLogger(Check.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (OntologyLoadException ex) {
            Logger.getLogger(Check.class.getName()).log(Level.SEVERE, null, ex);
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
