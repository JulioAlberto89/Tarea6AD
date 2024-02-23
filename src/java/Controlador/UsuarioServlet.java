/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Usuario
 */
@WebServlet(name = "UsuarioServlet", urlPatterns = {"/UsuarioServlet"})
public class UsuarioServlet extends HttpServlet {

    ConectorBD bd;

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
        // Aquí puedes procesar la solicitud para el registro de usuarios
        // Por ejemplo, puedes obtener los parámetros del formulario
        String nombre = request.getParameter("nombre");
        String email = request.getParameter("email");
        String contraseña = request.getParameter("contraseña");

        // Luego, puedes realizar cualquier validación necesaria de los datos del usuario
        // Por ejemplo, puedes verificar si el email ya está registrado en la base de datos
        // Si todo está bien, puedes redirigir al usuario a otra página, por ejemplo, una página de inicio de sesión
        response.sendRedirect("registrarusuario.jsp");
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

        System.out.println("Metodo get");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String accion = request.getParameter("accion");

        if (accion != null) {
            switch (accion) {
                case "registrar":
                    this.insertarUsuario(request, response);
                    break;

                case "buscar":
                    this.buscarUsuario(request, response);
                    break;

                default:
                    this.cargarPagina(request, response);
            }
        } else {
            this.cargarPagina(request, response);
        }
    }

    private void cargarPagina(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        bd = new ConectorBD();
        if (bd.conectar()) {
            /*
            List<Medico> medicos = bd.listar();

            // Calcula la suma de todas las tarifas
            float totalTarifas = 0;
            for (Medico medico : medicos) {
                totalTarifas += medico.getTarifa();
            }

            // Guarda la suma de todas las tarifas en un atributo de la solicitud
            request.setAttribute("totalTarifas", totalTarifas);

            // Guarda el tamaño de la lista de médicos en un atributo de la solicitud
            request.setAttribute("cantidadMedicos", medicos.size());

            request.setCharacterEncoding("UTF-8");
            response.setCharacterEncoding("UTF-8");
            request.setAttribute("medicos", medicos);
             */

            request.getRequestDispatcher("./registrarusuario.jsp").forward(request, response);
        }
    }
    
    private void cargarPaginaMedicos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        bd = new ConectorBD();
        if (bd.conectar()) {
            
            request.getRequestDispatcher("./medicos.jsp").forward(request, response);
        }
    }

    protected void insertarUsuario(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //String id = request.getParameter("id");
        String nombre = request.getParameter("nombre");
        String usuario = request.getParameter("usuario");
        String clave = request.getParameter("clave");
        if (bd.conectar()) {
            if (bd.altaUsuario(nombre, usuario, clave)) {
                this.cargarPagina(request, response);
            } else {
                System.out.println("Error al introducir usuario");
            }
        } else {
            System.out.println("No entro");
        }
    }

    protected void buscarUsuario(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String usuario = request.getParameter("usuario");
        String clave = request.getParameter("clave");

        // Crear una instancia de la clase ConectorBD
        ConectorBD bd = new ConectorBD();

        // Verificar si el usuario existe
        boolean usuarioExiste = bd.existeUsuario(usuario, clave);

        if (usuarioExiste) {
            // Si el usuario se encontró, cargar la página
            this.cargarPaginaMedicos(request, response);
        } else {
            // Si no se encontró el usuario, mostrar un mensaje de error
            System.out.println("Error: Usuario o contraseña incorrectos");
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
