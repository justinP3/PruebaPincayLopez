package LPApp;

public class LPKGD {
    private String LPcedula = "0941165318";
    private String LPnombre = "JustinPincay";
    private String LPUsuario = "patmic";
    private String LPClave = "123";

    public LPKGD() {

    }

    public LPKGD(String LPcedula, String LPnombre) {
    }

    public String getLPcedula() {
        return LPcedula;
    }

    public void setLPcedula(String lPcedula) {
        LPcedula = lPcedula;
    }

    public String getLPnombre() {
        return LPnombre;
    }

    public void setLPnombre(String lPnombre) {
        LPnombre = lPnombre;
    }

    public String getLPUsuario() {
        return LPUsuario;
    }

    public void setLPUsuario(String lPUsuario) {
        LPUsuario = lPUsuario;
    }

    public String getLPClave() {
        return LPClave;
    }

    public void setLPClave(String lPClave) {
        LPClave = lPClave;
    }

    private int intentosFallidos = 0;
    private static final int MAX_INTENTOS = 3;

    public void LPAutenticar() {
        java.util.Scanner scanner = new java.util.Scanner(System.in);

        while (intentosFallidos < MAX_INTENTOS) {
            System.out.println("\n====== AUTENTICACIÓN ======");
            System.out.println("Intentos restantes: " + (MAX_INTENTOS - intentosFallidos));

            System.out.print("Ingrese usuario: ");
            String usuarioIngresado = scanner.nextLine();

            System.out.print("Ingrese contraseña: ");
            String claveIngresada = scanner.nextLine();

            if (usuarioIngresado.equals(LPUsuario) && claveIngresada.equals(LPClave)) {
                System.out.println("\n======================================");
                System.out.println("\n Autenticación exitosa \n " + this.LPnombre + "\n " + this.LPcedula + "\n ");
                System.out.println("\n======================================");
                intentosFallidos = 0;
                return;
            } else {
                intentosFallidos++;
                if (intentosFallidos < MAX_INTENTOS) {
                    System.out.println("\n Usuario o contraseña incorrectos.");
                    System.out.println("Intentos restantes: " + (MAX_INTENTOS - intentosFallidos));
                } else {
                    System.out.println("\n Numero de intentos alcanzados");
                }
            }
        }
        scanner.close();
    }
}
