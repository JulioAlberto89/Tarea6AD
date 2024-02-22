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
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
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
                </section>

                <section class="col-md-9">
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
            </div>
        </div>

    </body>
</html>
