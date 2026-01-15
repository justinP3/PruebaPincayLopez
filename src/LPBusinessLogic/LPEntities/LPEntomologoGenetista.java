
package LPBusinessLogic.LPEntities;

import java.util.List;

import LPDataAcces.LPDAOs.LPAlimentoTipoDAO;
import LPDataAcces.LPDAOs.LPHormigaDAO;
import LPDataAcces.LPDTOs.LPAlimentoTipoDTO;
import LPInfrastructure.LPAppException;

public class LPEntomologoGenetista extends LPEntomologo {

    private String Genoma;

    public LPEntomologoGenetista() {
        super();
    }

    public String getGenoma() {
        return Genoma;
    }

    public void setGenoma(String genoma) {
        Genoma = genoma;
    }

    @Override
    public void LPPreparaAlimento(String tipoAlimento) throws LPAppException {
        System.out.println("\n[+] Preparando alimento con genoma: " + tipoAlimento);

        if (tipoAlimento == null || tipoAlimento.isEmpty()) {
            System.out.println("Error: Tipo de alimento nulo o vacio");
            return;
        }

        try {
            LPAlimentoTipoDAO alimentoDAO = new LPAlimentoTipoDAO();

            List<LPAlimentoTipoDTO> alimentos = alimentoDAO.readAll();

            LPAlimentoTipoDTO alimentoAEliminar = null;
            for (LPAlimentoTipoDTO alimento : alimentos) {
                if (alimento.getNombre().equals(tipoAlimento)) {
                    alimentoAEliminar = alimento;
                    break;
                }
            }

            if (alimentoAEliminar == null) {
                System.out.println("Alimento no encontrado: " + tipoAlimento);
                return;
            }

            alimentoDAO.delete(alimentoAEliminar.getIdAlimentoTipo());

            System.out.println("[Preparado y eliminado]: " + tipoAlimento);

        } catch (LPAppException e) {
            System.out.println("Error al preparar alimento: " + e.getMessage());
        }
    }

    public LPHormiga LPAlimentarAntGenetista(LPHormiga hormiga, String alimento, String genoma) throws LPAppException {

        System.out.println("\nALIMENTANDO HORMIGA CON GENOMA");

        if (hormiga == null || alimento == null || genoma == null) {
            System.out.println("Error: Hormiga, alimento o genoma nulo");
            return hormiga;
        }

        if (!genoma.equals("X") && !genoma.equals("XX") && !genoma.equals("XY")) {
            System.out.println("Error: Genoma invalido. Debe ser X, XX o XY");
            return null;
        }

        try {

            LPPreparaAlimento(alimento);

            if (alimento.equals("Nectarívoros")) {
                System.out.println("La hormiga fue alimentada correctamente con " + alimento + " y genoma " + genoma);

                if (genoma.equals("X") && hormiga instanceof LPHReina) {
                    System.out.println("HReina habilitada para SUPERREPRODUCCION");
                    LPsuperReproduccion((LPHReina) hormiga);
                    LPactualizarHormigaEnBD(hormiga);
                    return hormiga;
                }

                if (genoma.equals("X") && hormiga instanceof LPHLarva) {
                    System.out.println("HLarva transformada a HReina");
                    LPHReina nuevaReina = new LPHReina();
                    nuevaReina.data = hormiga.data;
                    nuevaReina.data.setIdHormigaTipo(5);
                    LPactualizarHormigaEnBD(nuevaReina);
                    return nuevaReina;
                }

                System.out.println("La hormiga SOBREVIVE con genoma " + genoma);
                LPactualizarHormigaEnBD(hormiga);
                return hormiga;
            } else {
                System.out.println("La hormiga necesita Nectarívoros, pero recibio: " + alimento);
                System.out.println("La hormiga MUERE");
                LPeliminarHormigaMuerta(hormiga);
                return null;
            }
        } catch (LPAppException e) {
            System.out.println("Error al alimentar hormiga: " + e.getMessage());
            return null;
        }
    }

    public void LPsuperReproduccion(LPHReina reina) {
        if (reina == null) {
            System.out.println("Error: Reina nula");
            return;
        }

        System.out.println("[SUPERREPRODUCCION INICIADA]");
        System.out.println("Reina " + reina.data.getNombre() + " en modo superReproduccion");
        System.out.println("Multiplicacion de la colonia iniciada...");
    }

    // refactor
    public void LPactualizarHormigaEnBD(LPHormiga hormiga) throws LPAppException {
        if (hormiga == null || hormiga.data == null || hormiga.data.getIdHormiga() == null) {
            System.out.println("Error: Hormiga sin ID valido para actualizar");
            return;
        }

        try {
            LPHormigaDAO hormigaDAO = new LPHormigaDAO();

            hormigaDAO.update(hormiga.data);

            System.out.println("Hormiga actualizada en la base de datos: " + hormiga.data.getNombre());

        } catch (LPAppException e) {
            System.out.println("Error al actualizar la hormiga: " + e.getMessage());
        }
    }
}
