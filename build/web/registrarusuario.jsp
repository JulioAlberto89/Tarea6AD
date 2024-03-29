<%-- 
    Document   : registrarusuario
    Created on : 22 feb. 2024, 12:30:00
    Author     : Usuario
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registro de Usuario</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
        <style>
            body {
                background-color: #f5f5f5;
            }

            .center-box {
                position: absolute;
                top: 50%;
                left: 50%;
                transform: translate(-50%, -50%);
                background-color: #ffffff;
                padding: 20px;
                border-radius: 10px;
                box-shadow: 0px 0px 10px 0px rgba(0,0,0,0.1);
            }
        </style>
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
        <!-- Verifica si hay un parámetro de error en la URL y muestra un SweetAlert si es así -->
    </head>
    <body>

        <div class="container">
            <div class="center-box col-md-6">
                <h2 class="text-center">Gestión Médicos</h2>
                <h5 class="text-center mb-4">Registro de Usuario</h5>
                <form action="UsuarioServlet" method="GET">
                    <div class="form-group">
                        <label for="nombre">Nombre:</label>
                        <input type="text" class="form-control" id="nombre" name="nombre" required>
                    </div>
                    <div class="form-group">
                        <label for="email">Email:</label>
                        <input type="email" class="form-control" id="usuario" name="usuario" required>
                    </div>
                    <div class="form-group">
                        <label for="password">Contraseña:</label>
                        <input type="password" class="form-control" id="clave" name="clave" required>
                    </div>
                    <div class="form-group">
                        <label for="confirmPassword">Repetir Contraseña:</label>
                        <input type="password" class="form-control" id="confirmPassword" name="confirmPassword" required>
                    </div>
                    <button type="submit" class="btn btn-primary btn-block" name="accion" value="registrar">Registro</button>
                </form>
                <p class="text-center mt-3"><a href="index.jsp">Volver al inicio</a></p>
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
                text: 'Datos de registro incorrectos'
            });
        </script>
        <%
            }
        %>



    </body>
</html>
