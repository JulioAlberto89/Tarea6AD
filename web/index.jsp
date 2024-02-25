<%-- 
    Document   : index
    Created on : 8 feb. 2024, 9:16:21
    Author     : Usuario
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Hospital</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
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
    </style>    
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    <!-- Verifica si hay un parámetro de error en la URL y muestra un SweetAlert si es así -->
</head>
<body>

    <div class="container">
        <div class="center-box col-md-6">
            <h2 class="text-center">Gestión Médicos</h2>
            <form action="./UsuarioServlet?accion=buscar" method="GET">
                <div class="form-group">
                    <label for="usuario">Email:</label>
                    <input type="usuario" class="form-control" id="usuario" name="usuario" required>
                </div>
                <div class="form-group">
                    <label for="clave">Contraseña:</label>
                    <input type="clave" class="form-control" id="clave" name="clave" required>
                </div>
                <button type="submit" class="btn btn-primary btn-block" name="accion" value="buscar">Iniciar Sesión</button>
            </form>
            <p class="text-center mt-3">¿Aún no tienes cuenta? <a href="./UsuarioServlet">Regístrate aquí</a></p>

            <p class="text-center mt-3"><a href="./MedicoServlet">Entrar como invitado</a></p>
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
            text: 'Datos de usuario incorrectos'
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
            title: '¡Registro exitoso!',
            text: '<%= successMessage%>'
        });
    </script>
    <%
            // Limpiar el mensaje de éxito después de mostrar la alerta
            request.getSession().removeAttribute("successMessage");
        }
    %>
</body>
</html>
