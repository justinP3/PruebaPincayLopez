package LPApp;

public class LPKGD {
    private String LPcedula = "0941165318";
    private String LPnombre = "JustinPincay";
    private String LPUsuario = "patmic";
    private String LPClave = "123";
    private int intentosFallidos = 0;
    private static final int MAX_INTENTOS = 3;

    public LPKGD() {
    }

    public LPKGD(String LPcedula, String LPnombre) {
    }

    public boolean LPAutenticar() {
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
                return true;
            } else {
                intentosFallidos++;
                System.out.println("\n Usuario o contraseña incorrectos.");
            }
        }

        System.out.println("\n======================================");
        System.out.println(" HA EXCEDIDO EL NÚMERO DE INTENTOS.");
        System.out.println(" El sistema se cerrará.");
        System.out.println("======================================");
        scanner.close();
        return false;
    }
}