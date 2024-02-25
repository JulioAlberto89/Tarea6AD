<%-- 
    Document   : menu
    Created on : 25 feb 2024, 20:55:33
    Author     : Julio A Mayoral
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Menú</title>
        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
        <!-- Font Awesome CSS -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
        <style>
            /* Ajustes de estilo personalizados */
            .navbar-brand {
                font-size: 24px; /* Tamaño de fuente más grande para el título */
            }
            .welcome-message {
                font-size: 18px; /* Tamaño de fuente más grande para el mensaje de bienvenida */
            }
        </style>
    </head>
    <body>
        <!-- Barra superior -->
        <nav class="navbar navbar-expand-lg navbar-light bg-light mb-4">
            <div class="container">
                <a class="navbar-brand" href="MedicoServlet?accion=cargarPagina">Gestión Médicos</a>
            </div>
        </nav>

        <!-- Barra inferior -->
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <div class="container">
                <!-- Logo -->
                <a class="navbar-brand" href="MedicoServlet?accion=cargarPagina">
                    <img src="https://cdn-icons-png.flaticon.com/512/994/994873.png" alt="Icono Médicos" style="width: 30px; height: 30px; margin-right: 5px;"> Médicos
                </a>
                <!-- Cerrar Sesión -->
                <form class="form-inline ml-auto">
                    <a href="index.jsp" class="btn btn-outline-danger mr-2">Cerrar Sesión</a>
                </form>
            </div>
        </nav>

        <!-- Contenido principal -->
        <div class="container mt-4">
            <div class="row">
                <div class="col-md-12 text-center">
                    <p class="welcome-message">Bienvenidos al portal de médicos, <%= request.getAttribute("nombreUsuario")%></p>
                </div>
            </div>
        </div>

        <!-- Bootstrap JS y dependencias -->
        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    </body>
</html>