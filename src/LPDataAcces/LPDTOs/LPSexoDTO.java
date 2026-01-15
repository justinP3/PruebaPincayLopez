package LPDataAcces.LPDTOs;

public class LPSexoDTO {
    private Integer IdSexo;
    private String Nombre;
    private String FechaCreacion;
    private String FechaModifica;

    public LPSexoDTO() {
    }

    public LPSexoDTO(String nombre) {
        IdSexo = 0;
        Nombre = nombre;
    }

    public LPSexoDTO(Integer idSexo, String nombre, String fechaCreacion,
            String fechaModifica) {
        IdSexo = idSexo;
        Nombre = nombre;
        FechaCreacion = fechaCreacion;
        FechaModifica = fechaModifica;
    }

    public Integer getIdSexo() {
        return IdSexo;
    }

    public void setIdSexo(Integer idSexo) {
        IdSexo = idSexo;
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
                + "\n IdSexo        : " + getIdSexo()
                + "\n Nombre        : " + getNombre()
                + "\n FechaCreacion : " + getFechaCreacion()
                + "\n FechaModifica : " + getFechaModifica();
    }
}
