package LPDataAcces.LPDTOs;

public class LPAlimentoTipoDTO {
    private Integer IdAlimentoTipo;
    private String Nombre;
    private String FechaCreacion;
    private String FechaModifica;

    public LPAlimentoTipoDTO() {
    }

    public LPAlimentoTipoDTO(String nombre) {
        IdAlimentoTipo = 0;
        Nombre = nombre;
    }

    public LPAlimentoTipoDTO(Integer idAlimentoTipo, String nombre, String fechaCreacion, String fechaModifica) {
        IdAlimentoTipo = idAlimentoTipo;
        Nombre = nombre;
        FechaCreacion = fechaCreacion;
        FechaModifica = fechaModifica;
    }

    public Integer getIdAlimentoTipo() {
        return IdAlimentoTipo;
    }

    public void setIdAlimentoTipo(Integer idAlimentoTipo) {
        IdAlimentoTipo = idAlimentoTipo;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
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
                + "\n IdAlimentoTipo: " + getIdAlimentoTipo()
                + "\n Nombre        : " + getNombre()
                + "\n FechaCreacion : " + getFechaCreacion()
                + "\n FechaModifica : " + getFechaModifica()
                + "\n --------------------------- ";
    }
}
