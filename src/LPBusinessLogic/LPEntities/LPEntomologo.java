package LPBusinessLogic.LPEntities;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import LPBusinessLogic.LPInterfaces.LPIEntomologo;
import LPDataAcces.LPDAOs.LPAlimentoTipoDAO;
import LPDataAcces.LPDAOs.LPHormigaDAO;
import LPDataAcces.LPDTOs.LPAlimentoTipoDTO;
import LPDataAcces.LPDTOs.LPHormigaDTO;
import LPInfrastructure.LPAppException;

public class LPEntomologo implements LPIEntomologo {

    private static final String blanco = "\u001B[0m";
    private static final String rojo = "\u001B[31m";
    private static final String azul = "\u001B[34m";

    protected static final Set<String> HormigasAceptadas = new HashSet<>(Arrays.asList("HReina", "HLarva"));
    protected static final Set<String> AlimentosAceptados = new HashSet<>(
            Arrays.asList("Nectarívoros", "Carnívoro", "Omnívoro", "Herbívoro", "Insectívoro"));

    public List<String> LPetlAntNest() {
        List<String> hormigasValidas = new ArrayList<>();
        List<String> todosLosDatos = new ArrayList<>();
        System.out.println("[+] Hormigas");
        try (BufferedReader reader = new BufferedReader(
                new java.io.InputStreamReader(
                        new java.io.FileInputStream("LPstorage\\LPDataFiles\\LPAntNest.txt"),
                        StandardCharsets.UTF_8))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] elementos = linea.split(",");
                for (String elemento : elementos) {
                    String elementoLimpio = elemento.trim();
                    if (!elementoLimpio.isEmpty()) {
                        todosLosDatos.add(elementoLimpio);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
            return hormigasValidas;
        }
        for (String dato : todosLosDatos) {
            int gjIndex;
            String gjColor = azul;
            String gjAnimation = "|/-\\";
            for (int gjI = 0; gjI <= 100; gjI++) {
                gjIndex = gjI % gjAnimation.length();
                System.out.print("\r" + gjColor + gjAnimation.charAt(gjIndex) + blanco);
                try {
                    Thread.sleep(1);
                } catch (InterruptedException gjException) {
                    System.out.println(gjException.getMessage());
                }
            }
            boolean esValido = HormigasAceptadas.contains(dato);
            if (esValido == true) {
                System.out.println("| " + azul + dato + blanco);
                hormigasValidas.add(dato);
            } else {
                System.out.println("| " + rojo + dato + blanco);
            }
        }
        return hormigasValidas;
    }

    public void LPguardarHormigasEnBD(List<String> hormigasValidas) throws LPAppException {
        if (hormigasValidas == null || hormigasValidas.isEmpty()) {
            System.out.println("\n" + rojo + "No hay hormigas válidas para guardar" + blanco);
            return;
        }

        LPHormigaDAO hormigaDAO = new LPHormigaDAO();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String fechaActual = LocalDateTime.now().format(formatter);
        Random random = new Random();

        System.out.println("\n[+] Guardando hormigas en la base de datos...");

        for (String tipoHormiga : hormigasValidas) {
            try {
                LPHormigaDTO hormiga = new LPHormigaDTO();

                int idTipo = tipoHormiga.equals("HReina") ? 5 : 1;
                hormiga.setIdHormigaTipo(idTipo);

                String nombreUnico = tipoHormiga + "-" + (1000 + random.nextInt(9000));
                hormiga.setNombre(nombreUnico);

                hormiga.setIdSexo(1);
                hormiga.setIdEstado(1);
                hormiga.setEstado("A");
                hormiga.setFechaCreacion(fechaActual);
                hormiga.setFechaModifica(fechaActual);

                hormigaDAO.create(hormiga);
                System.out.println("  -> Hormiga guardada: " + nombreUnico);

            } catch (LPAppException e) {
                System.out.println("Error al guardar " + tipoHormiga + ": " + e.getMessage());
            }
        }
    }

    public List<String> LPetlAntFood() {
        List<String> alimentosValidos = new ArrayList<>();
        List<String> todosLosDatos = new ArrayList<>();

        System.out.println("[+] Alimentos");

        try (BufferedReader reader = new BufferedReader(
                new java.io.InputStreamReader(
                        new java.io.FileInputStream("LPstorage\\LPDataFiles\\LPAntFood.txt"),
                        StandardCharsets.UTF_8))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] elementos = linea.split("-");
                for (String elemento : elementos) {
                    String elementoLimpio = elemento.trim();
                    if (!elementoLimpio.isEmpty()) {
                        todosLosDatos.add(elementoLimpio);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
            return alimentosValidos;
        }

        for (String dato : todosLosDatos) {
            String gjColor = rojo;
            String[] gjAnimation = { "o0o", "0o0", "o0o", "0o0" };

            for (int gjI = 0; gjI <= 100; gjI++) {
                int gjIndex = gjI % gjAnimation.length;
                System.out.print("\r" + gjColor + gjAnimation[gjIndex] + blanco);
                try {
                    Thread.sleep(1);
                } catch (InterruptedException gjException) {
                    System.out.println(gjException.getMessage());
                }
            }

            boolean esValido = AlimentosAceptados.contains(dato);

            if (esValido) {
                System.out.println(" " + azul + dato + blanco);
                alimentosValidos.add(dato);
            } else {
                System.out.println(" " + rojo + dato + blanco);
            }
        }

        return alimentosValidos;
    }

    public void LPguardarAlimentoEnBD(List<String> alimentosValidos) throws LPAppException {
        if (alimentosValidos == null || alimentosValidos.isEmpty()) {
            System.out.println("No hay alimentos válidos para guardar");
            return;
        }

        LPAlimentoTipoDAO alimentoDAO = new LPAlimentoTipoDAO();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String fechaActual = LocalDateTime.now().format(formatter);

        System.out.println("\n[+] Guardando alimentos en la base de datos...");

        for (String tipoAlimento : alimentosValidos) {
            try {
                LPAlimentoTipoDTO alimento = new LPAlimentoTipoDTO();

                alimento.setNombre(tipoAlimento);
                alimento.setFechaCreacion(fechaActual);
                alimento.setFechaModifica(fechaActual);

                alimentoDAO.create(alimento);

            } catch (LPAppException e) {
                System.out.println("Error al guardar " + tipoAlimento + ": " + e.getMessage());
            }
        }
    }

    public void LPPreparaAlimento(String tipoAlimento) throws LPAppException {
        System.out.println("\n[+] Preparando alimento: " + tipoAlimento);

        if (tipoAlimento == null || tipoAlimento.isEmpty()) {
            System.out.println("Error: Tipo de alimento nulo o vacío");
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

            System.out.println(azul + "[Preparado y eliminado]: " + tipoAlimento);

        } catch (LPAppException e) {
            System.out.println("Error al preparar alimento: " + e.getMessage());
        }
    }

    public LPHormiga LPAlimentarAnt(LPHormiga hormiga, String alimento) {

        System.out.println("\nALIMENTANDO HORMIGA SIN GENOMA");

        if (hormiga == null || alimento == null) {
            System.out.println("Error: Hormiga o alimento nulo");
            return hormiga;
        }

        try {
            LPPreparaAlimento(alimento);

            if (alimento.equals("Nectarívoros")) {
                System.out.println("La hormiga fue alimentada correctamente con " + alimento);
                System.out.println("La hormiga SOBREVIVE");
                return hormiga;
            } else {
                System.out.println("La hormiga necesita Nectarívoros, pero recibió: " + alimento);
                System.out.println("La hormiga MUERE");
                LPeliminarHormigaMuerta(hormiga);
                return null;
            }
        } catch (LPAppException e) {
            System.out.println("Error al alimentar hormiga: " + e.getMessage());
            return null;
        }
    }

    public void LPeliminarHormigaMuerta(LPHormiga hormiga) {
        if (hormiga == null || hormiga.data == null || hormiga.data.getIdHormiga() == null) {
            System.out.println("No se puede eliminar: Hormiga sin ID válido");
            return;
        }

        try {
            LPHormigaDAO hormigaDAO = new LPHormigaDAO();
            Integer hormigaId = hormiga.data.getIdHormiga();

            hormigaDAO.delete(hormigaId);

            System.out.println("La hormiga ha sido eliminada de la base de datos");

        } catch (LPAppException e) {
            System.out.println("Error al eliminar la hormiga: " + e.getMessage());
        }
    }
}