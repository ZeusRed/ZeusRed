/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Logica.UsuarioManager;
import Modelo.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

@WebServlet(name = "EditarUsuario", urlPatterns = {"/EditarUsuario"})
public class EditarUsuario extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            int valorSubmit = Integer.parseInt(request.getParameter("submitUsuario"));
            if (valorSubmit == 2) {
                int id = Integer.parseInt(request.getParameter("id"));

                String nombre = request.getParameter("nombre");
                String app = request.getParameter("apPaterno");

                String apm = request.getParameter("apMaterno");

                String correo = request.getParameter("correo");

                String password = request.getParameter("password");
                String passwordConfirmar = request.getParameter("passwordConfirmar");
                if (password.equals(passwordConfirmar)) {

                    Usuario usuario = new Usuario();
                    usuario.setIdUsuario(id);
                    usuario.setNombre(nombre);
                    usuario.setApPaterno(app);
                    usuario.setApMaterno(apm);
                    usuario.setCorreo(correo);
                    usuario.setPassword(password);
                    UsuarioManager manager = new UsuarioManager();
                    int registro = manager.ActualizaUsuario(usuario);
                    if (registro == 1) {
                        UsuarioManager mn = new UsuarioManager();
                        List<Usuario> usuarios = mn.GetUsuarios();
                        request.setAttribute("listaUsuariosRender", usuarios);
                        request.getRequestDispatcher("usuarios.jsp").forward(request, response);
                    } else {

                        String error = "Error al generar el registro del  usuario";
                        request.setAttribute("error", error);

                        request.setAttribute("usuarioRender", usuario);
                        request.getRequestDispatcher("/Vistas/AccionesUsuario/EditarUsuario.jsp").forward(request, response);

                    }

                } else {
                    Usuario usuario = new Usuario();
                    usuario.setIdUsuario(id);
                    usuario.setNombre(nombre);
                    usuario.setApPaterno(app);
                    usuario.setApMaterno(apm);
                    usuario.setCorreo(correo);
                    usuario.setPassword(password);
                    String error = "Error  las contraseñas deben coincidir ";
                    request.setAttribute("error", error);
                    request.setAttribute("usuarioRender", usuario);
                    request.getRequestDispatcher("/Vistas/AccionesUsuario/EditarUsuario.jsp").forward(request, response);
                }
            } else {
                UsuarioManager mn3 = new UsuarioManager();
                List<Usuario> usuarios = mn3.GetUsuarios();
                request.setAttribute("listaUsuariosRender", usuarios);
                request.getRequestDispatcher("usuarios.jsp").forward(request, response);
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
