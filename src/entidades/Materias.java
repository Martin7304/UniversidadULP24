package entidades;

public class Materias {
    
    private int idMateria;
    private String nombre;
    private int anio;
    private boolean estado;
    
    public Materias() {} // Constructor vacio.

    // Constructor completo.
    public Materias(int idMateria, String nombre, int anio, boolean estado) {
        this.idMateria = idMateria;
        this.nombre = nombre;
        this.anio = anio;
        this.estado = estado;
    }

    // Constructor sin el atributo idMateria.
    public Materias(String nombre, int anio, boolean estado) {
        this.nombre = nombre;
        this.anio = anio;
        this.estado = estado;
    }

    // Metodos Getter and Setter.
    public int getIdMateria() {
        return idMateria;
    }

    public void setIdMateria(int idMateria) {
        this.idMateria = idMateria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Materia: " + "\n\nIdMateria = " + idMateria + "\n\nNombre = " + nombre + "\n\nAño = " + anio + "\n\nEstado = " + estado;
    }
    
}
