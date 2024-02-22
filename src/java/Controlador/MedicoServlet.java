/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Medico;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Usuario
 */
@WebServlet(name = "MedicoServlet", urlPatterns = {"/MedicoServlet"})
public class MedicoServlet extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Medico</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Medico at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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

        System.out.println("Metodo get");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String accion = request.getParameter("accion");

        if (accion != null) {
            switch (accion) {
                case "editar":
                    this.editarMedico(request, response);
                    break;

                case "eliminar":
                    this.eliminarMedico(request, response);
                    System.out.println("Id médico: " + request.getParameter("id"));
                    break;

                default:
                    this.cargarPagina(request, response);
            }
        } else {
            this.cargarPagina(request, response);
        }
    }

    /*
    private void cargarPagina(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        bd = new ConectorBD();
        if (bd.conectar()) {
            List<Medico> medicos = bd.listar();
            System.out.println("Medico" + medicos);
            request.setCharacterEncoding("UTF-8");
            response.setCharacterEncoding("UTF-8");
            request.setAttribute("medicos", medicos);

            request.getRequestDispatcher("./medicos.jsp").forward(request, response);
        }
    }
    */
    private void cargarPagina(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    bd = new ConectorBD();
    if (bd.conectar()) {
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

        request.getRequestDispatcher("./medicos.jsp").forward(request, response);
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

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        bd = new ConectorBD();
        String accion = request.getParameter("accion");
        if (accion != null) {
            switch (accion) {
                case "insertar":
                    this.insertarMedico(request, response);
                    break;
                case "modificar":
                    this.modificarMedico(request, response);
                    break;
                case "editar":
                    this.editarMedico(request, response);
                    break;
                case "eliminar":
                    this.eliminarMedico(request, response);
                    break;
                default:
                    this.cargarPagina(request, response);
            }
        } else {
            this.cargarPagina(request, response);
        }
    }

    protected void modificarMedico(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String id = request.getParameter("id");
        String nombre = request.getParameter("nombre");
        String sala = request.getParameter("sala");
        String especialidad = request.getParameter("especialidad");
        String tarifa = request.getParameter("tarifa");

        if (id != null && !id.isEmpty() && sala != null && !sala.isEmpty() && tarifa != null && !tarifa.isEmpty()) {
            try {
                if (bd.conectar()) {
                    if (bd.actMedico(Integer.parseInt(id), nombre, Float.parseFloat(sala), especialidad, Integer.parseInt(tarifa))) {
                        System.out.println("modificado");
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: No se pudo convertir algún valor a número.");
                e.printStackTrace();
            }
        } else {
            // Manejar el caso en que alguno de los parámetros está vacío
            System.out.println("Alguno de los parámetros está vacío. No se puede modificar el médico.");
        }
        this.cargarPagina(request, response);
    }

    protected void editarMedico(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String id = request.getParameter("id");
        Medico medico = new Medico();
        if (bd.conectar()) {
            medico = bd.buscarMedico(id);
        }

        request.setAttribute("amod", medico);
        String jspEditar = "./editarmedicos.jsp";
        request.getRequestDispatcher(jspEditar).forward(request, response);
        return;
    }

    protected void eliminarMedico(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("Método eliminar médico");
        System.out.println("Id médico: " + request.getParameter("id"));
        String id = request.getParameter("id");
        if (bd.conectar()) {
            if (bd.eliminarMedico(Integer.parseInt(id))) {
                this.cargarPagina(request, response);
            }
        }
        this.cargarPagina(request, response);
    }

    protected void insertarMedico(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //String id = request.getParameter("id");
        String nombre = request.getParameter("nombre");
        String sala = request.getParameter("sala");
        String especialidad = request.getParameter("especialidad");
        String tarifa = request.getParameter("tarifa");
        if (bd.conectar()) {
            if (bd.altaMedico(nombre, Float.parseFloat(sala), especialidad, Integer.parseInt(tarifa))) {
                this.cargarPagina(request, response);
            } else {
                System.out.println("Error al introducir medico");
            }
        } else {
            System.out.println("No entro");
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
