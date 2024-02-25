<%-- 
    Document   : editarmedicos
    Created on : 8 feb. 2024, 10:55:06
    Author     : Usuario
--%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="Modelo.Medico"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Editar Médico</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
    </head>
    <body>

        <div class="container">
            <h1 class="text-center">Editar Médico</h1>

            <div class="row">
                <section class="col-md-6 offset-md-3">
                    <form action="./MedicoServlet?accion=modificar&id=${amod.getIdMedico()}" method="POST" accept-charset="UTF-8">
                        <div><input type="hidden" name="id" value="${amod.idMedico}"></div>

                        <div class="form-group">
                            <label for="nombre">Nombre:</label>
                            <input class="form-control" id="nombre" type="text" name="nombre" value="${amod.getNombre()}">
                        </div>

                        <div class="form-group">
                            <label for="sala">Sala:</label>
                            <input class="form-control" id="sala" type="text" name="sala"  value="${amod.getSala()}">
                        </div>

                        <div class="form-group">
                            <label for="especialidad">Especialidad:</label>
                            <input class="form-control" id="especialidad" type="text" name="especialidad" value="${amod.getEspecialidad()}">
                        </div>

                        <div class="form-group">
                            <label for="tarifa">Tarifa:</label>
                            <input class="form-control" id="tarifa" type="text" name="tarifa" value="${amod.getTarifa()}">
                        </div>

                        <button type="submit" class="btn btn-primary">Guardar cambios</button>
                    </form>
                </section>

            </div>
        </div>

        <script>
            function validarFormulario() {
                var sala = document.getElementById("sala").value;
                var tarifa = document.getElementById("tarifa").value;

                if (isNaN(sala) || isNaN(tarifa)) {
                    alert("Por favor, ingrese un valor numérico para la sala y la tarifa.");
                    return false; // Detiene el envío del formulario si alguno de los campos no es numérico
                }
                return true; // Permite el envío del formulario si ambos campos son numéricos
            }
        </script>
        

    </body>
</html>