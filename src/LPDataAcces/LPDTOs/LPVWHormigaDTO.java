package LPDataAcces.LPDTOs;

public class LPVWHormigaDTO {
    private String IdHormiga;
    private String Tipo;
    private String Sexo;
    private String EstadoHormiga;
    private String Nombre;
    private String Estado;
    private String FechaCreacion;
    private String FechaModifica;

    public LPVWHormigaDTO(String idHormiga, String tipo, String sexo, String estadoHormiga, String nombre,
            String estado, String fechaCreacion, String fechaModifica) {
        IdHormiga = idHormiga;
        Tipo = tipo;
        Sexo = sexo;
        EstadoHormiga = estadoHormiga;
        Nombre = nombre;
        Estado = estado;
        FechaCreacion = fechaCreacion;
        FechaModifica = fechaModifica;
    }

    public String getIdHormiga() {
        return IdHormiga;
    }

    public void setIdHormiga(String idHormiga) {
        IdHormiga = idHormiga;
    }

    public String getTipo() {
        return Tipo;
    }

    public void setTipo(String tipo) {
        Tipo = tipo;
    }

    public String getSexo() {
        return Sexo;
    }

    public void setSexo(String sexo) {
        Sexo = sexo;
    }

    public String getEstadoHormiga() {
        return EstadoHormiga;
    }

    public void setEstadoHormiga(String estadoHormiga) {
        EstadoHormiga = estadoHormiga;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String estado) {
        Estado = estado;
    }

    public String getFechaCreacion() {
        return FechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        FechaCreacion = fechaCreacion;
    }

    public String getFechaModifica() {
        return FechaModifica;
    }

    public void setFechaModifica(String fechaModifica) {
        FechaModifica = fechaModifica;
    }

    @Override
    public String toString() {
        return getClass().getName()
                + "\n  IdHormiga        : " + getIdHormiga()
                + "\n  Tipo            : " + getTipo()
                + "\n  Sexo            : " + getSexo()
                + "\n  EstadoHormiga   : " + getEstadoHormiga()
                + "\n  Nombre          : " + getNombre()
                + "\n  Estado          : " + getEstado()
                + "\n  FechaCreacion   : " + getFechaCreacion()
                + "\n  FechaModifica   : " + getFechaModifica()
                + "\n-----------------------------------";
    }
}
