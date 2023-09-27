package entidades;

public class Inscripcion {
    
    private int idInscripcion;
    private Alumno alumno;
    private Materias materia;
    private int nota;
    
    public Inscripcion() {} // Constructor vacio.

    // Constructor completo.
    public Inscripcion(int idInscripcion, Alumno alumno, Materias materia, int nota) {
        this.idInscripcion = idInscripcion;
        this.alumno = alumno;
        this.materia = materia;
        this.nota = nota;
    }

    public Inscripcion(Alumno alumno, Materias materia) {
        this.alumno = alumno;
        this.materia = materia;
    }

    // Constructor sin el atributo idInscripcion.
    public Inscripcion(Alumno alumno, Materias materia, int nota) {
        this.alumno = alumno;
        this.materia = materia;
        this.nota = nota;
    }

    // Metodos Getter and Setter.
    public int getIdInscripcion() {
        return idInscripcion;
    }

    public void setIdInscripcion(int idInscripcion) {
        this.idInscripcion = idInscripcion;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public Materias getMateria() {
        return materia;
    }

    public void setMateria(Materias materia) {
        this.materia = materia;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }
    
}
