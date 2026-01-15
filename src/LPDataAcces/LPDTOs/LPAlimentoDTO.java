package LPDataAcces.LPDTOs;

public class LPAlimentoDTO {
    private Integer IdAlimento;
    private Integer IdAlimentoTipo;
    private String Nombre;
    private String FechaCreacion;
    private String FechaModifica;

    public LPAlimentoDTO() {
    }

    public LPAlimentoDTO(String nombre) {
        IdAlimentoTipo = 0;
        Nombre = nombre;
    }

    public LPAlimentoDTO(Integer idAlimento, Integer idAlimentoTipo, String nombre, String fechaCreacion,
            String fechaModifica) {
        IdAlimento = idAlimento;
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

    public Integer getIdAlimento() {
        return IdAlimento;
    }

    public void setIdAlimento(Integer idAlimento) {
        IdAlimento = idAlimento;
    }

    @Override
    public String toString() {
        return getClass().getName()
                + "\n IdAlimento    : " + getIdAlimento()
                + "\n IdAlimentoTipo: " + getIdAlimentoTipo()
                + "\n Nombre        : " + getNombre()
                + "\n FechaCreacion : " + getFechaCreacion()
                + "\n FechaModifica : " + getFechaModifica()
                + "\n --------------------------- ";
    }
}
