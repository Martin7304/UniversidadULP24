
package accesoadatos;

import entidades.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class InscripcionData {
    private Connection conex;
    private AlumnoData aluData;
    private MateriaData matData;
    
    public InscripcionData() {
        this.conex = Conexion.getConexion();
    }
    
    public void guardarInscripcion(Inscripcion insc) {
        String sql = "INSERT INTO inscripcion (alumno, materia, nota) "
                    + "VALUES (?, ?, ?)";

        try {
            PreparedStatement ps = conex.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            ps.setObject(1, insc.getAlumno());
            ps.setObject(2, insc.getMateria());
            ps.setInt(3, insc.getNota());

            ResultSet rs = ps.getGeneratedKeys();//tabla de inscripcion

            if (rs.next()) {
                insc.setIdInscripcion(rs.getInt(1));
                JOptionPane.showMessageDialog(null, "Inscripción guardada.");
            }
            
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla de inscripción. " + ex.getMessage());
        }
    }
    
    public List<Inscripcion> obtenerInscripciones() {
        List<Inscripcion> inscripciones = new ArrayList<>();
        try {
            String sql = "SELECT * FROM inscripcion";

            PreparedStatement ps = conex.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Inscripcion inscripcion = new Inscripcion();
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla inscripción. " + ex.getMessage());
        }
        return inscripciones;
    }
    
    /*
        Este método debe obtener las inscripciones del alumno
        pasandole el id del alumno por parametro
    */
    public List<Inscripcion> obtenerInscripcionesPorAlumno(int id) {
        List<Inscripcion> inscripciones = new ArrayList();
        try {
            String listar = "SELECT inscripcion.idInscripcion, nota, " // Se recupera de la tabla inscripción
                          + "FROM inscripcion JOIN alumno "
                          + "ON (inscripcion.idAlumno = alumno.idAlumno) "
                          + "WHERE inscripcion.idAlumno = ?";
            
            PreparedStatement ps = conex.prepareStatement(listar);
            ps.setInt(1, id);
            
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                Inscripcion inscripcion = new Inscripcion();
                inscripcion.setIdInscripcion(rs.getInt("idInscripcion"));
                inscripcion.setNota(rs.getInt("nota"));
                inscripciones.add(inscripcion);
            }
            
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a las tablas inscripcion y/o materia. " + ex.getMessage());
        }
        return inscripciones;
    }
    
    // Este método recibe el id de un alumno.
    public List<Materias> obtenerMateriasCursadas(int id) {
  ArrayList<Materias> materias= new ArrayList<>();
         
         String sql = "SELECT inscripcion.idMateria, nombre, año FROM inscripcion, materia WHERE inscripcion.idMateria = materia.idMateria AND "
                 + "inscripcion.idAlumno = ?";
         
        try {
            PreparedStatement ps=conex.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                
                Materias materia = new Materias();
                materia.setIdMateria(rs.getInt("idMateria"));
                materia.setNombre(rs.getString("nombre"));
                materia.setAnio(rs.getInt("año"));
                materias.add(materia);
                
            }
            ps.close();
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla inscripcion");
        }
        return materias;
        
    }
    
   public List<Materias> obtenerMateriasNoCursadas(int id) {
        List<Materias> materias = new ArrayList<>();
        try {
            String listar = "SELECT materia.idMateria, nombre, año "
                          + "FROM inscripcion JOIN materia "
                          + "ON NOT (inscripcion.idMateria = materia.idMateria) "
                          + "WHERE inscripcion.idAlumno = ?";
        ///// Arreglar para que traiga las materia donde no esta inscripto
            
            PreparedStatement ps = conex.prepareStatement(listar);
            ps.setInt(1, id);
            
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                Materias materia = new Materias();
                materia.setIdMateria(rs.getInt("idMateria"));
                materia.setNombre(rs.getString("nombre"));
                materia.setAnio(rs.getInt("año"));
                materias.add(materia);
            }
            
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a las tablas inscripcion y/o materia. " + ex.getMessage());
        }
        return materias;
    }
    
    public void borrarInscripcionMateriaAlumno(int idAlumno, int idMateria) {
        try {
            String borrado = "DELETE FROM inscripcion WHERE idAlumno = ? AND idMateria = ?";
            PreparedStatement ps = conex.prepareStatement(borrado);
            ps.setInt(1, idAlumno);
            ps.setInt(2, idMateria);
            
            int resultado = ps.executeUpdate();
            
            if (resultado == 1) {
                JOptionPane.showMessageDialog(null, "La inscripción se eliminó exitosamente.");
            } else {
                JOptionPane.showMessageDialog(null, "Hubo un problema al borrar la inscripción.");
            }
            
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla inscripcion. " + ex.getMessage());
        }
    }
    
    public void actualizarNota(int idAlumno, int idMateria, int nota) {
        try {
            String borrado = "UPDATE inscripcion SET nota = ? WHERE idAlumno = ? AND idMateria = ?";
            PreparedStatement ps = conex.prepareStatement(borrado);
            ps.setInt(1, nota);
            ps.setInt(2, idAlumno);
            ps.setInt(3, idMateria);
            
            int resultado = ps.executeUpdate();
            
            if (resultado == 1) {
                JOptionPane.showMessageDialog(null, "La nota del alumno se modificó exitosamente.");
            } else {
                JOptionPane.showMessageDialog(null, "Hubo un problema al modificar la nota.");
            }
            
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla inscripción. " + ex.getMessage());
        }
    }
    
    public List<Alumno> obtenerAlumnosPorMateria(int idMateria) {
        List<Alumno> alumnos = new ArrayList();
        try {
            String obtencion = "SELECT alumno.idAlumno, dni, apellido, nombre FROM alumno "
                             + "JOIN inscripcion ON (inscripcion.idAlumno = alumno.idAlumno) "
                             + "WHERE inscripcion.idMateria = ?";
            PreparedStatement ps = conex.prepareStatement(obtencion);
            ps.setInt(1, idMateria);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Alumno alumno = new Alumno();
                alumno.setDni(rs.getInt("dni"));
                alumno.setApellido(rs.getString("apellido"));
                alumno.setNombre(rs.getString("nombre"));
                alumnos.add(alumno);
            }
            
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a las tablas alumno y/o inscripción. " + ex.getMessage());
        }
        return alumnos;
    }
    
}