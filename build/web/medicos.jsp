<%-- 
    Document   : medicos
    Created on : 8 feb. 2024, 9:18:32
    Author     : Usuario
--%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8");%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="Modelo.Medico"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Gestión de Médicos</title>
        <!-- Aquí verificamos si hay un mensaje de error y lo mostramos -->
        <c:if test="${not empty error}">
        <div class="alert alert-danger" role="alert">
            ${error}
        </div>
    </c:if>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    <!-- Verifica si hay un parámetro de error en la URL y muestra un SweetAlert si es así -->
</head>
<body>

    <div class="container">
        <h1 class="text-center">Gestión de Médicos</h1>

        <div class="row">
            <section class="col-md-3">
                <form action="./MedicoServlet?accion=insertar" method="POST" accept-charset="UTF-8">

                    <div><input type="hidden" name="id" value="${medico.getIdMedico()}"></div>
                    <div class="form-group"><label for="nombre">Nombre:</label><input class="form-control" type="text" name="nombre" value="" ></div>
                    <div class="form-group"><label for="sala">Sala:</label><input class="form-control" type="text" name="sala" value=""></div>
                    <div class="form-group"><label for="especialidad">Especialidad:</label><input class="form-control" type="text" name="especialidad" value=""></div>
                    <div class="form-group"><label for="tarifa">Tarifa:</label><input class="form-control" type="text" name="tarifa" value=""></div>

                    <input type="submit" value="insertar" class="btn btn-outline-primary">
                </form>

                <p class="btn btn-outline-secondary mt-3"><a href="index.jsp">Volver al inicio</a></p>
            </section>

            <section class="col-md-6">
                <table class="table table-sm table-hover">
                    <thead class="thead-dark">
                    <th>id</th>
                    <th>nombre</th>
                    <th>sala</th>
                    <th>especialidad</th>
                    <th>tarifa</th>
                    <th>acciones</th>
                    </thead>
                    <tbody>
                        <c:forEach items="${medicos}" var="medico">
                            <tr>
                                <td>${medico.getIdMedico()}</td>
                                <td>${medico.getNombre()}</td>
                                <td>${medico.getSala()}</td>
                                <td>${medico.getEspecialidad()}</td>
                                <td>${medico.getTarifa()}</td>
                                <td class="row"> 
                                    <a class="btn btn-outline-warning"  href="./MedicoServlet?accion=editar&id=${medico.getIdMedico()}"><i class="fa fa-pencil-square-o" aria-hidden="true"></i></a> &nbsp;
                                    <a class="btn btn-outline-danger" href="./MedicoServlet?accion=eliminar&id=${medico.getIdMedico()}"><i class="fa fa-trash-o" aria-hidden="true"></i></a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </section>
            <section class="col-md-3">
                <!-- Contenedor del total de tarifas -->
                <div class="card bg-danger text-white">
                    <div class="card-body">
                        <h5 class="card-title">Total de Tarifas</h5>
                        <p class="card-text">${totalTarifas}</p>
                    </div>
                </div>

                <!-- Contenedor de la cantidad de médicos -->
                <div class="card bg-success text-white mt-3">
                    <div class="card-body">
                        <h5 class="card-title">Cantidad de Médicos</h5>
                        <p class="card-text">${cantidadMedicos}</p>
                    </div>
                </div>
            </section>
        </div>
    </div>



    <%
        String error = request.getParameter("error");
        if (error != null && !error.isEmpty())
        {
    %>
    <script>
        Swal.fire({
            icon: 'error',
            title: 'Oops...',
            text: 'Datos de entrada incorrectos'
        });
    </script>
    <%
        }
    %>

    <%
        String successMessage = (String) request.getSession().getAttribute("successMessage");
        if (successMessage != null && !successMessage.isEmpty())
        {
    %>
    <script>
        Swal.fire({
            icon: 'success',
            title: '¡Modificación exitosa!',
            text: '<%= successMessage%>'
        });
    </script>
    <%
            // Limpiar el mensaje de éxito después de mostrar la alerta
            request.getSession().removeAttribute("successMessage");
        }
    %>
    <!-- Mensaje de borrado -->
    <%
        String successMessageBorrar = (String) request.getSession().getAttribute("successMessageEliminado");
        if (successMessageBorrar != null && !successMessageBorrar.isEmpty())
        {
    %>
    <script>
        Swal.fire({
            icon: 'success',
            title: '¡Borrado exitoso!',
            text: '<%= successMessageBorrar%>'
        });
    </script>
    <%
            // Limpiar el mensaje de éxito después de mostrar la alerta
            request.getSession().removeAttribute("successMessageEliminado");
        }
    %>
    <!--Insertar médico-->
    <%
        String successMessageInsertado = (String) request.getSession().getAttribute("successMessageInsertado");
        if (successMessageInsertado != null && !successMessageInsertado.isEmpty())
        {
    %>
    <script>
        Swal.fire({
            icon: 'success',
            title: '¡Inserción exitosa!',
            text: '<%= successMessageInsertado%>'
        });
    </script>
    <%
            // Limpiar el mensaje de éxito después de mostrar la alerta
            request.getSession().removeAttribute("successMessageInsertado");
        }
    %>
</body>
</html>
