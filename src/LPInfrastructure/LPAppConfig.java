package LPInfrastructure;

public abstract class LPAppConfig {
    // Paths Storage
    public static final String DATABASE = "jdbc:sqlite:LPstorage\\LPDatabases\\LPAntFood.sqlite";
    public static final String DATAFILEA = "LPstorage\\LPDataFiles\\LPAntFood.csv";
    public static final String DATAFILEH = "LPstorage\\LPDataFiles\\LPAntNest.csv";
    public static final String LOGFILE = "LPstorage\\LPLogs\\LPAppErrors.log";

    // AppMSGs
    public static final String MSG_DEFAULT_ERROR = "Ups! Error inesperado. Por favor, contacte al administrador del sistema.";
    public static final String MSG_DEFAULT_CLASS = "undefined";
    public static final String MSG_DEFAULT_METHOD = "undefined";

    private LPAppConfig() {
    }
}
