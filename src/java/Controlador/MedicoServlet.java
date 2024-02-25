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
@WebServlet(name = "MedicoServlet", urlPatterns =
{
    "/MedicoServlet"
})
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
        try ( PrintWriter out = response.getWriter())
        {
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

        if (accion != null)
        {
            switch (accion)
            {
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
        } else
        {
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
        if (bd.conectar())
        {
            List<Medico> medicos = bd.listar();

            // Calcula la suma de todas las tarifas
            float totalTarifas = 0;
            for (Medico medico : medicos)
            {
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

    private void cargarPaginaEditar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        bd = new ConectorBD();
        if (bd.conectar())
        {

            request.getRequestDispatcher("./editarmedicos.jsp").forward(request, response);
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
        if (accion != null)
        {
            switch (accion)
            {
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
        } else
        {
            this.cargarPagina(request, response);
        }
    }

    /*
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
     */
    protected void modificarMedico(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String id = request.getParameter("id");
        String nombre = request.getParameter("nombre");
        String salaStr = request.getParameter("sala");
        String especialidad = request.getParameter("especialidad");
        String tarifaStr = request.getParameter("tarifa");

        if (id != null && !id.isEmpty() && nombre != null && !nombre.isEmpty()
                && salaStr != null && !salaStr.isEmpty() && especialidad != null && !especialidad.isEmpty()
                && tarifaStr != null && !tarifaStr.isEmpty())
        {

            try
            {
                float sala = Float.parseFloat(salaStr);
                int tarifa = Integer.parseInt(tarifaStr);

                if (sala > 0 && tarifa > 0)
                {
                    if (bd.conectar() && bd.actMedico(Integer.parseInt(id), nombre, sala, especialidad, tarifa))
                    {
                        // Redirigir a la página de médicos después de la modificación exitosa
                        cargarPagina(request, response);
                        return;
                    }
                }
            } catch (NumberFormatException e)
            {
                // Setear un atributo en la solicitud para indicar el error
                request.setAttribute("error", "Los valores de sala o tarifa no son numéricos.");
            }
        } else
        {
            // Setear un atributo en la solicitud para indicar el error
            request.setAttribute("error", "Todos los campos son requeridos.");
        }

        // Si llega aquí, la modificación falló o los datos ingresados son incorrectos
        // Volver a cargar la página actual
        cargarPagina(request, response);
    }


    /*
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
     */
 /*
    protected void editarMedico(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String id = request.getParameter("id");
        Medico medico = new Medico();
        if (bd.conectar())
        {
            medico = bd.buscarMedico(id);
            if (medico != null)
            {
                request.setAttribute("amod", medico);
            } else
            {
                // Manejar el caso donde medico no se encontró
                // Por ejemplo, redirigir a una página de error
                response.sendRedirect("error.jsp");
                return;
            }
        } else
        {
            // Manejar el caso donde la conexión a la base de datos falló
            // Por ejemplo, redirigir a una página de error
            response.sendRedirect("error.jsp");
            return;
        }

        String jspEditar = "./editarmedicos.jsp";
        request.getRequestDispatcher(jspEditar).forward(request, response);
    }
     */
    protected void editarMedico(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String id = request.getParameter("id");
        Medico medico = new Medico();
        try
        {
            bd = new ConectorBD();
            if (bd.conectar())
            {
                medico = bd.buscarMedico(id);
                if (medico != null)
                {
                    request.setAttribute("amod", medico);
                } else
                {
                    // Manejar el caso donde medico no se encontró
                    // Por ejemplo, redirigir a una página de error
                    response.sendRedirect("error.jsp");
                    return;
                }
            } else
            {
                // Manejar el caso donde la conexión a la base de datos falló
                // Por ejemplo, redirigir a una página de error
                response.sendRedirect("error.jsp");
                return;
            }
        } catch (Exception e)
        {
            // Manejar el caso de cualquier excepción durante la conexión a la base de datos
            // Por ejemplo, redirigir a una página de error
            response.sendRedirect("error.jsp");
            return;
        }

        String jspEditar = "./editarmedicos.jsp";
        request.getRequestDispatcher(jspEditar).forward(request, response);
    }

    protected void eliminarMedico(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("Método eliminar médico");
        System.out.println("Id médico: " + request.getParameter("id"));
        String id = request.getParameter("id");
        if (bd.conectar())
        {
            if (bd.eliminarMedico(Integer.parseInt(id)))
            {
                this.cargarPagina(request, response);
            }
        }
        this.cargarPagina(request, response);
    }

    /*
    protected void eliminarMedico(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("Método eliminar médico");
        System.out.println("Id médico: " + request.getParameter("id"));
        String id = request.getParameter("id");

        // Verificar si bd es nulo antes de usarlo
        if (bd != null && bd.conectar())
        {
            if (bd.eliminarMedico(Integer.parseInt(id)))
            {
                this.cargarPagina(request, response);
                return; // Salir del método después de cargar la página
            }
        } else
        {
            // Manejar el caso donde bd es null o la conexión falla
            // Aquí puedes imprimir un mensaje de error o realizar alguna acción adecuada
            System.out.println("Error: bd es null o la conexión falló");
        }
    }
     */

    protected void insertarMedico(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String nombre = request.getParameter("nombre");
        String salaStr = request.getParameter("sala");
        String especialidad = request.getParameter("especialidad");
        String tarifaStr = request.getParameter("tarifa");

        if (nombre != null && !nombre.isEmpty()
                && salaStr != null && !salaStr.isEmpty()
                && especialidad != null && !especialidad.isEmpty()
                && tarifaStr != null && !tarifaStr.isEmpty())
        {

            try
            {
                float sala = Float.parseFloat(salaStr);
                int tarifa = Integer.parseInt(tarifaStr);

                if (sala > 0 && tarifa > 0)
                {
                    if (bd.conectar() && bd.altaMedico(nombre, sala, especialidad, tarifa))
                    {
                        // Redirigir a la página de médicos después de la inserción exitosa
                        //cargarPagina(request, response);
                        //request.getSession().setAttribute("successMessage", "insert");
                        cargarPagina(request, response);
                        return;
                    }
                }
            } catch (NumberFormatException e)
            {
                // Setear un atributo en la solicitud para indicar el error
                request.setAttribute("error", "Los valores de sala o tarifa no son numéricos.");
            }
        } else
        {
            // Setear un atributo en la solicitud para indicar el error
            request.setAttribute("error", "Todos los campos son requeridos.");
        }

        // Si llega aquí, la inserción falló o los datos ingresados son incorrectos
        // Volver a cargar la página actual
        cargarPagina(request, response);
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
