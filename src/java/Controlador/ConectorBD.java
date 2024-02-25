/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Medico;
import Modelo.Usuario;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Usuario
 */
public class ConectorBD {

    private final String bd = "tarea6";
    private final String url = "jdbc:mysql://localhost/";
    private final String driver = "com.mysql.cj.jdbc.Driver";
    private Connection conn = null;
    private final String login = "root";
    private final String password = "";
    Statement stmt = null;
    PreparedStatement pstmt = null;

    public ConectorBD() {
    }

    public Connection getConexion() {
        return conn;
    }

    public boolean conectar() {
        boolean b = false;

        try
        {
            //Obtenemos el driver de mysql
            Class.forName(driver);
            //Establecemos conexión
            conn = DriverManager.getConnection(url + bd, login, password);

            if (conn != null)
            {
                System.out.println("Conexión a " + bd + " establecida correctamente.");
                b = true;
            }

        } catch (ClassNotFoundException ex)
        {
            Logger.getLogger(ConectorBD.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex)
        {
            Logger.getLogger(ConectorBD.class.getName()).log(Level.SEVERE, null, ex);
        }

        return b;
    }

    public boolean close() {
        boolean b = false;
        if (this.conn != null)
        {
            try
            {
                conn.close();
                conn = null;
                b = true;

                System.out.println("La conexión a la base de datos " + bd + " se ha terminado.");
            } catch (SQLException ex)
            {
                Logger.getLogger(ConectorBD.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return b;
    }

    public boolean altaMedico(String nombre, Float sala, String especialidad, Integer tarifa) {
        boolean b = false;
        String query;

        query = "INSERT INTO `medico` ( `nombre`,`sala`,`especialidad`,`tarifa`) VALUES "
                + "( '" + nombre + "', '" + sala + "','" + especialidad + "','" + tarifa + "')";

        try
        {
            System.out.println("Conexion: " + conn.getCatalog());
            stmt = conn.createStatement();
            stmt.executeUpdate(query);
            b = true;
        } catch (SQLException ex)
        {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        return b;
    }

    public Medico buscarMedico(String id) {
        Medico medico = null;

        try
        {
            Statement orden = conn.createStatement();
            ResultSet query = orden.executeQuery("select * from medico where id_medico = '" + id + "'");

            if (query.next())
            {
                medico = new Medico();

                medico.setIdMedico(Integer.parseInt(query.getString("id_medico")));
                medico.setNombre(query.getString("nombre"));
                medico.setSala(Float.parseFloat(query.getString("sala")));
                medico.setEspecialidad(query.getString("especialidad"));
                medico.setTarifa(Integer.parseInt(query.getString("tarifa")));

            }
        } catch (SQLException ex)
        {
            Logger.getLogger(ConectorBD.class.getName()).log(Level.SEVERE, null, ex);
        }

        return medico;
    }

    public boolean actMedico(Integer id, String nombre, Float sala, String especialidad, Integer tarifa) {
        boolean b = false;
        String query;

        query = "UPDATE medico SET nombre ='" + nombre + "',sala='" + sala + "',especialidad='" + especialidad + "',tarifa='" + tarifa
                + "' WHERE id_medico ='" + id + "'";

        try
        {
            System.out.println("Conexion: " + conn.getCatalog());
            stmt = conn.createStatement();
            stmt.executeUpdate(query);
            b = true;
        } catch (SQLException ex)
        {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        return b;
    }

    public boolean eliminarMedico(int id) {

        boolean b = false;

        try
        {
            Statement orden = conn.createStatement();
            orden.executeUpdate("delete from medico where id_medico = '" + id + "'");
            orden.close();
            b = true;
        } catch (SQLException ex)
        {
            Logger.getLogger(ConectorBD.class.getName()).log(Level.SEVERE, null, ex);
        }

        return b;
    }

    public ArrayList<Medico> listar() {
        ArrayList<Medico> listaMedicos = new ArrayList<>();

        try
        {
            Statement orden = conn.createStatement();
            ResultSet query = orden.executeQuery("select * from medico");

            while (query.next())
            {
                Medico medico = new Medico();
                medico.setIdMedico(query.getInt("id_medico"));
                medico.setNombre(query.getString("nombre"));
                medico.setSala(query.getFloat("sala"));
                medico.setEspecialidad(query.getString("especialidad"));
                medico.setTarifa(query.getInt("tarifa"));

                listaMedicos.add(medico);
            }
        } catch (SQLException ex)
        {
            Logger.getLogger(ConectorBD.class.getName()).log(Level.SEVERE, null, ex);
        }

        return listaMedicos;
    }

    ////////////////////////////////Usuario//////////////////////////////////
    public boolean altaUsuario(String nombre, String usuario, String password) {
        boolean b = false;
        String query;

        query = "INSERT INTO `usuario` ( `nombre`,`usuario`,`clave`) VALUES "
                + "( '" + nombre + "', '" + usuario + "','" + password + "')";

        try
        {
            System.out.println("Conexion: " + conn.getCatalog());
            stmt = conn.createStatement();
            stmt.executeUpdate(query);
            b = true;
        } catch (SQLException ex)
        {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        return b;
    }

    public boolean existeUsuario(String nombreUsuario, String clave) {
        boolean usuarioExiste = false;

        conectar();

        try
        {
            PreparedStatement statement = conn.prepareStatement("SELECT COUNT(*) AS count FROM usuario WHERE usuario = ? AND clave = ?");
            statement.setString(1, nombreUsuario);
            statement.setString(2, clave);

            ResultSet query = statement.executeQuery();

            if (query.next())
            {
                int count = query.getInt("count");
                usuarioExiste = count > 0;
            }
        } catch (SQLException ex)
        {
            Logger.getLogger(ConectorBD.class.getName()).log(Level.SEVERE, null, ex);
        }

        return usuarioExiste;
    }

    public String buscarNombreUsuario(String nombreUsuario, String clave) {
        String nombre = null;

        conectar();

        try
        {
            PreparedStatement statement = conn.prepareStatement("SELECT nombre FROM usuario WHERE usuario = ? AND clave = ?");
            statement.setString(1, nombreUsuario);
            statement.setString(2, clave);

            ResultSet query = statement.executeQuery();

            if (query.next())
            {
                nombre = query.getString("nombre");
            }
        } catch (SQLException ex)
        {
            Logger.getLogger(ConectorBD.class.getName()).log(Level.SEVERE, null, ex);
        }

        return nombre;
    }

}
