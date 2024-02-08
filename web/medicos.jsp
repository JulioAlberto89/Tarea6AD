<%-- 
    Document   : medicos
    Created on : 8 feb. 2024, 9:18:32
    Author     : Usuario
--%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="Modelo.Medico"%> <!-- Actualizado -->

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Gestión de Médicos</title> <!-- Actualizado -->
    <!-- Agrega tus enlaces de estilos y scripts aquí -->
</head>
<body>

<div class="container">
    <h1 class="text-center">Gestión de Médicos</h1> <!-- Actualizado -->

    <div class="row">
        <section class="col-md-3">
            <form action="./Medicos?accion=insertar" method="POST" accept-charset="UTF-8"> <!-- Actualizado -->

                <div><input type="hidden" name="id" value="${medico.getIdMedico()}"></div> <!-- Actualizado -->
                <div class="form-group"><label for="nombre">Nombre:</label><input class="form-control" type="text" name="nombre" value="" ></div> <!-- Actualizado -->
                <div class="form-group"><label for="sala">Sala:</label><input class="form-control" type="text" name="sala" value=""></div> <!-- Actualizado -->
                <div class="form-group"><label for="especialidad">Especialidad:</label><input class="form-control" type="text" name="especialidad" value=""></div> <!-- Actualizado -->
                <div class="form-group"><label for="tarifa">Tarifa:</label><input class="form-control" type="text" name="tarifa" value=""></div> <!-- Actualizado -->

                <input type="submit" value="insertar" class="btn btn-outline-primary">
            </form>
        </section>

        <section class="col-md-9">
            <table class="table table-sm table-hover">
                <thead class="thead-dark">
                    <th>id</th>
                    <th>nombre</th> <!-- Actualizado -->
                    <th>sala</th> <!-- Actualizado -->
                    <th>especialidad</th> <!-- Actualizado -->
                    <th>tarifa</th> <!-- Actualizado -->
                    <th>acciones</th>
                </thead>
                <tbody>
                    <c:forEach items="${medicos}" var="medico"> <!-- Actualizado -->
                        <tr>
                            <td>${medico.getIdMedico()}</td> <!-- Actualizado -->
                            <td>${medico.getNombre()}</td> <!-- Actualizado -->
                            <td>${medico.getSala()}</td> <!-- Actualizado -->
                            <td>${medico.getEspecialidad()}</td> <!-- Actualizado -->
                            <td>${medico.getTarifa()}</td> <!-- Actualizado -->
                            <td class="row"> 
                                <a class="btn btn-outline-warning"  href="./Medicos?accion=editar&id=${medico.getIdMedico()}"><i class="fa fa-pencil-square-o" aria-hidden="true"></i></a> &nbsp;
                                <a class="btn btn-outline-danger" href="./Medicos?accion=eliminar&id=${medico.getIdMedico()}"><i class="fa fa-trash-o" aria-hidden="true"></i></a>
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
