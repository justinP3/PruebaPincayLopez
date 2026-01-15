package LPBusinessLogic.LPEntities;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
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
        try (BufferedReader reader = new BufferedReader(new FileReader("LPstorage\\LPDataFiles\\LPAntNest.txt"))) {
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

    public void LPguardarAlimentoEnBD(List<String> hormigasValidas) throws LPAppException {
        if (hormigasValidas == null || hormigasValidas.isEmpty()) {
            System.out.println("\n" + rojo + "No hay hormigas válidas para guardar" + blanco);
            return;
        }

        LPHormigaDAO hormigaDAO = new LPHormigaDAO();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String fechaActual = LocalDateTime.now().format(formatter);

        System.out.println("\n[+] Guardando hormigas en la base de datos...");

        for (String tipoHormiga : hormigasValidas) {
            try {
                LPHormigaDTO hormiga = new LPHormigaDTO();

                // Asignar tipo de hormiga (5 para HReina, 1 para HLarva)
                int idTipo = tipoHormiga.equals("HReina") ? 5 : 1;
                hormiga.setIdHormigaTipo(idTipo);

                // Valores por defecto
                hormiga.setIdSexo(1);
                hormiga.setIdEstado(1);
                hormiga.setNombre(tipoHormiga);
                hormiga.setEstado("A");
                hormiga.setFechaCreacion(fechaActual);
                hormiga.setFechaModifica(fechaActual);

                // Guardar en base de datos
                hormigaDAO.create(hormiga);

            } catch (LPAppException e) {
                System.out.println("Error al guardar " + tipoHormiga + ": " + e.getMessage());
            }
        }
    }

    public List<String> LPetlAntFood() {
        List<String> alimentosValidos = new ArrayList<>();
        List<String> todosLosDatos = new ArrayList<>();

        System.out.println("[+] Alimentos");

        try (BufferedReader reader = new BufferedReader(new FileReader("LPstorage\\LPDataFiles\\LPAntFood.txt"))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                // Separar por guiones y procesar cada elemento
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

        // Procesar y mostrar cada dato con el spinner de espera
        for (String dato : todosLosDatos) {
            String gjColor = rojo;
            String[] gjAnimation = { "o0o", "0o0", "o0o", "0o0" }; // Ida y vuelta

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

    public void LPguardarHormigasEnBD(List<String> alimentosValidos) throws LPAppException {
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

                // Guardar en base de datos
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

            // Obtener todos los alimentos para encontrar el ID
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

            // Eliminar el alimento de la base de datos
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
            // Preparar el alimento (lo elimina de la BD)
            LPPreparaAlimento(alimento);

            // Validar que el alimento sea "Nectarívoros" (solo pueden comer néctar)
            if (alimento.equals("Nectarívoros")) {
                System.out.println("La hormiga fue alimentada correctamente con " + alimento);
                System.out.println("La hormiga SOBREVIVE");
                return hormiga;
            } else {
                System.out.println("La hormiga necesita Nectarívoros, pero recibió: " + alimento);
                System.out.println("La hormiga MUERE");
                LPeliminarHormigaMuerta(hormiga);
                return null; // La hormiga muere
            }
        } catch (LPAppException e) {
            System.out.println("Error al alimentar hormiga: " + e.getMessage());
            return null;
        }
    }

    // refactor
    public void LPeliminarHormigaMuerta(LPHormiga hormiga) {
        if (hormiga == null || hormiga.data == null || hormiga.data.getIdHormiga() == null) {
            System.out.println("No se puede eliminar: Hormiga sin ID válido");
            return;
        }

        try {
            LPHormigaDAO hormigaDAO = new LPHormigaDAO();
            Integer hormigaId = hormiga.data.getIdHormiga();

            // Eliminar de la base de datos pasando el ID
            hormigaDAO.delete(hormigaId);

            System.out.println("La hormiga ha sido eliminada de la base de datos");

        } catch (LPAppException e) {
            System.out.println("Error al eliminar la hormiga: " + e.getMessage());
        }
    }
}