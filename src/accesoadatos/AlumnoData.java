
package accesoadatos;

import entidades.Alumno;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class AlumnoData {
    
    private Connection conex;
    
    /* Constructor:
        Al hacer un new AlumnoData(), se crea la conexión.
        Se llama al método getConexion() y se almacena en
        el atributo conex de tipo Connection.
    */
    public AlumnoData() {
        this.conex = Conexion.getConexion();
    }
    
    public void guardarAlumno(Alumno alumno) {
        try {
            String nuevoAlumno = "INSERT INTO alumno (dni, apellido, nombre, fechaNacimiento, estado) "
                                + "VALUES (?, ?, ?, ?, ?)";
            
            PreparedStatement ps = conex.prepareStatement(nuevoAlumno, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, alumno.getDni());
            ps.setString(2, alumno.getApellido());
            ps.setString(3, alumno.getNombre());
            ps.setDate(4, Date.valueOf(alumno.getFechaNacimiento()));
            ps.setBoolean(5, alumno.isEstado());
            ps.executeUpdate();
            
            ResultSet rs = ps.getGeneratedKeys();
            
            if (rs.next()) {
                //alumno.setIdAlumno(rs.getInt("idAlumno"));
                JOptionPane.showMessageDialog(null, "¡Nuevo alumno añadido con exito!");
            }
            
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al guardar el Alumno. " + ex.getMessage());
        }
    }
    
    public Alumno buscarAlumno(int id) {
        Alumno alumno = null;
        try {
            String busqueda = "SELECT dni, apellido, nombre, fechaNacimiento, estado "
                            + "FROM alumno WHERE id = ? AND estado = 1";
            
            PreparedStatement ps = conex.prepareStatement(busqueda);
            ps.setInt(1, id);
            
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                alumno = new Alumno();
                alumno.setIdAlumno(id);
                alumno.setDni(rs.getInt("dni"));
                alumno.setApellido(rs.getString("apellido"));
                alumno.setNombre(rs.getString("nombre"));
                alumno.setFechaNacimiento(rs.getDate("fechaNacimiento").toLocalDate());
                alumno.setEstado(true);
            } else {
                JOptionPane.showMessageDialog(null, "No existe el alumno.");
            }
            
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla alumno. " + ex.getMessage());
        }
        return alumno;
    }
    
    public Alumno buscarAlumnoPorDni(int dni) {
        Alumno alumno = null;
        try {
            String busqueda = "SELECT idAlumno, dni, apellido, nombre, fechaNacimiento, estado "
                            + "FROM alumno WHERE dni = ? AND estado = 1";
            
            PreparedStatement ps = conex.prepareStatement(busqueda);
            ps.setInt(1, dni);
            
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                alumno = new Alumno();
                alumno.setIdAlumno(rs.getInt("idAlumno"));
                alumno.setDni(rs.getInt("dni"));
                alumno.setApellido(rs.getString("apellido"));
                alumno.setNombre(rs.getString("nombre"));
                alumno.setFechaNacimiento(rs.getDate("fechaNacimiento").toLocalDate());
                alumno.setEstado(true);
                JOptionPane.showMessageDialog(null, alumno.toString());
            } else {
                JOptionPane.showMessageDialog(null, "No existe el alumno.");
            }
            
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla alumno. " + ex.getMessage());
        }
        return alumno;
    }
    
    public List<Alumno> listarAlumnos() {
        List<Alumno> alumnos = new ArrayList();
        try {
            String listar = "SELECT * FROM alumno WHERE estado = 1";
            
            PreparedStatement ps = conex.prepareStatement(listar);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                Alumno alumno = new Alumno();
                alumno.setIdAlumno(rs.getInt("idAlumno"));
                alumno.setDni(rs.getInt("dni"));
                alumno.setApellido(rs.getString("apellido"));
                alumno.setNombre(rs.getString("nombre"));
                alumno.setFechaNacimiento(rs.getDate("fechaNacimiento").toLocalDate());
                alumno.setEstado(rs.getBoolean("estado"));
                alumnos.add(alumno); // Se agrega el alumno creado arriba al arrayList.
            }
            
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla alumno. " + ex.getMessage());
        }
        return alumnos;
    }
    
    public void modificarAlumno(Alumno alumno) {
        try {
            String modificacion = "UPDATE alumno SET dni = ?, apellido = ?, nombre = ?, fechaNacimiento = ?, estado = ?"
                                + "WHERE idAlumno = ?";
            
            PreparedStatement ps = conex.prepareStatement(modificacion);
            ps.setInt(1, alumno.getDni());
            ps.setString(2, alumno.getApellido());
            ps.setString(3, alumno.getNombre());
            ps.setDate(4, Date.valueOf(alumno.getFechaNacimiento()));
            ps.setBoolean(5, alumno.isEstado());
            ps.setInt(6, alumno.getIdAlumno());
            
            int resultado = ps.executeUpdate();
            
            if (resultado == 1) {
                JOptionPane.showMessageDialog(null, "El alumno se modificó exitosamente.");
            } else {
                JOptionPane.showMessageDialog(null, "Error al modificar el alumno.");
            }
            
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla alumno. " + ex.getMessage());
        }
    }
    
    public void eliminarAlumno(int id) {
        try {
            String modificacion = "UPDATE alumno SET estado = 0 WHERE idAlumno = ?";
            
            PreparedStatement ps = conex.prepareStatement(modificacion);
            ps.setInt(1, id);
            
            int resultado = ps.executeUpdate();
            
            if (resultado == 1) {
                JOptionPane.showMessageDialog(null, "El alumno se eliminó exitosamente.");
            } else {
                JOptionPane.showMessageDialog(null, "Error al eliminar el alumno.");
            }
            
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla alumno. " + ex.getMessage());
        }
    }
    
}
