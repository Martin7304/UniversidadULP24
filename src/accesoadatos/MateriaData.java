package accesoadatos;

import entidades.Materias;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class MateriaData {
    private Connection conex;
    
    /* Constructor:
        Al hacer un new MateriaData(), se crea la conexión.
        Se llama al método getConexion() y se almacena en
        el atributo conex de tipo Connection.
    */
    public MateriaData() {
        this.conex = Conexion.getConexion();
    }
    
    public void guardarMateria(Materias materias) {
        try {
            String nuevaMateria = "INSERT INTO materia (nombre, año, estado) "
                    + "VALUES (?, ?, ?)";
            
            PreparedStatement ps = conex.prepareStatement(nuevaMateria, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, materias.getNombre());
            ps.setInt(2, materias.getAnio());
            ps.setBoolean(3, materias.isEstado());
            ps.executeUpdate();
            
            ResultSet rs = ps.getGeneratedKeys();
            
            if (rs.next()) {
                //materias.setIdMateria(rs.getInt("idMateria"));
                JOptionPane.showMessageDialog(null, "¡Nueva materia añadida con exito!");
            }
            
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al guardar la materia. " + ex.getMessage());
        }
    }
    
    public Materias buscarMateria(int id) {
        Materias materia = null;
        try {
            String busqueda = "SELECT nombre, año, estado FROM materia WHERE idMateria = ? AND estado = 1";
            
            PreparedStatement ps = conex.prepareStatement(busqueda);
            ps.setInt(1, id);
            
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                materia = new Materias();
                materia.setIdMateria(id);
                materia.setNombre(rs.getString("nombre"));
                materia.setAnio(rs.getInt("año"));
                materia.setEstado(true);
                 JOptionPane.showMessageDialog(null, materia.toString());
            } else {
                JOptionPane.showMessageDialog(null, "No existe la materia.");
            }
            
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla materia. " + ex.getMessage());
        }
        return materia;
    }
    
    public void modificarMateria(Materias materia) {
        try {
            String modificacion = "UPDATE materia SET nombre = ?, año = ?, estado = ? WHERE idMateria = ?";
            
            PreparedStatement ps = conex.prepareStatement(modificacion);
            ps.setString(1, materia.getNombre());
            ps.setInt(2, materia.getAnio());
            ps.setBoolean(3, materia.isEstado());
            ps.setInt(4, materia.getIdMateria());
            
            int resultado = ps.executeUpdate();
            
            if (resultado == 1) {
                JOptionPane.showMessageDialog(null, "La materia se modificó exitosamente.");
            } else {
                JOptionPane.showMessageDialog(null, "Error al modificar la materia.");
            }
            
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla materia. " + ex.getMessage());
        }
    }
    
    public void eliminarMateria(int id) {
        try {
            String modificacion = "UPDATE materia SET estado = 0 WHERE idMateria = ?";
            
            PreparedStatement ps = conex.prepareStatement(modificacion);
            ps.setInt(1, id);
            
            int resultado = ps.executeUpdate();
            
            if (resultado == 1) {
                JOptionPane.showMessageDialog(null, "La materia se eliminó exitosamente.");
            } else {
                JOptionPane.showMessageDialog(null, "Error al eliminar la materia.");
            }
            
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla materia. " + ex.getMessage());
        }
    }
    
    
    public void ReactivarMateria(int id) {
        try {
            String modificacion = "UPDATE materia SET estado = 1 WHERE idMateria = ?";
            
            PreparedStatement ps = conex.prepareStatement(modificacion);
            ps.setInt(1, id);
            
            int resultado = ps.executeUpdate();
            
            if (resultado == 1) {
                JOptionPane.showMessageDialog(null, "La materia se activo correctamente.");
            } else {
                JOptionPane.showMessageDialog(null, "Error al activar la materia.");
            }
            
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla materia. " + ex.getMessage());
        }
    }
    
    public List<Materias> listarMaterias() {
        List<Materias> materias = new ArrayList();
        try {
            String listar = "SELECT * FROM materia";
            
            PreparedStatement ps = conex.prepareStatement(listar);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                Materias materia = new Materias();
                materia.setIdMateria(rs.getInt("idMateria"));
                materia.setNombre(rs.getString("nombre"));
                materia.setAnio(rs.getInt("año"));
                materia.setEstado(rs.getBoolean("estado"));
                materias.add(materia); // Se agregar la materia creada arriba al arrayList.
            }
            
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla materia. " + ex.getMessage());
        }
        return materias;
    }
}
