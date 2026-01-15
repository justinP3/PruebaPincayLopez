import LPApp.LPKGD;
import LPBusinessLogic.LPEntities.LPEntomologo;
import LPBusinessLogic.LPEntities.LPEntomologoGenetista;
import LPBusinessLogic.LPEntities.LPHReina;
import LPBusinessLogic.LPEntities.LPHLarva;
import LPInfrastructure.LPAppException;
import java.util.List;

public class App {

    public static void main(String[] args) {

        try {
            System.out.println("=== SISTEMA DE GESTION DE HORMIGAS ===\n");
            LPKGD bloquer = new LPKGD("0941165318", "JustinPincay");

            if (!bloquer.LPAutenticar()) {
                return;
            }

            LPEntomologo entomologo = new LPEntomologo();

            System.out.println("\n Leyendo hormigas del archivo...");
            List<String> hormigasValidas = entomologo.LPetlAntNest();

            System.out.println("\n Guardando hormigas en base de datos...");
            entomologo.LPguardarHormigasEnBD(hormigasValidas);

            System.out.println("\n Leyendo alimentos del archivo...");
            List<String> alimentosValidos = entomologo.LPetlAntFood();

            System.out.println("\n Guardando alimentos en base de datos...");
            entomologo.LPguardarAlimentoEnBD(alimentosValidos);

            System.out.println("\n Alimentando hormiga sin genoma...");
            LPHLarva larva = new LPHLarva();
            larva.data.setIdHormiga(1);
            larva.data.setNombre("Larva-1");
            LPHLarva resultadoLarva = (LPHLarva) entomologo.LPAlimentarAnt(larva, "Nectarívoros");

            if (resultadoLarva != null) {
                System.out.println("Larva sobrevivio exitosamente");
            }

            System.out.println("\n\n=== TRABAJANDO CON ENTOMOLOGO GENETISTA ===");
            LPEntomologoGenetista entomologoGen = new LPEntomologoGenetista();

            System.out.println("\n Alimentando HReina con genoma X (superReproduccion)...");
            LPHReina reina = new LPHReina();
            reina.data.setIdHormiga(2);
            reina.data.setNombre("Reina-1");
            reina.data.setIdHormigaTipo(5);
            LPHReina resultadoReina = (LPHReina) entomologoGen.LPAlimentarAntGenetista(reina, "Nectarívoros", "X");

            if (resultadoReina != null) {
                System.out.println("Reina en modo superReproduccion");
            }

            System.out.println("\n Alimentando HLarva con genoma X (transformacion)...");
            LPHLarva larva2 = new LPHLarva();
            larva2.data.setIdHormiga(3);
            larva2.data.setNombre("Larva-2");
            larva2.data.setIdHormigaTipo(1);
            LPHReina resultadoTransformacion = (LPHReina) entomologoGen.LPAlimentarAntGenetista(larva2, "Nectarívoros",
                    "X");

            if (resultadoTransformacion != null) {
                System.out.println("Larva transformada exitosamente a Reina");
            }

            System.out.println("\n Alimentando hormiga con alimento incorrecto...");
            LPHLarva larva3 = new LPHLarva();
            larva3.data.setIdHormiga(4);
            larva3.data.setNombre("Larva-3");
            LPHLarva resultadoMuerte = (LPHLarva) entomologo.LPAlimentarAnt(larva3, "Carnívoro");

            if (resultadoMuerte == null) {
                System.out.println("Hormiga eliminda correctamente de la base de datos");
            }

            System.out.println("\n\n=== PROCESO COMPLETADO EXITOSAMENTE ===");

        } catch (LPAppException e) {
            System.out.println("Error durante la ejecucion: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Error inesperado: " + e.getMessage());
            e.printStackTrace();
        }
    }
}