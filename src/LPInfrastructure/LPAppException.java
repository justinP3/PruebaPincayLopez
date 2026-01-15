package LPInfrastructure;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import LPInfrastructure.LPTools.LPCMDColor;

public class LPAppException extends Exception {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public LPAppException(String showMsg) {
        super((showMsg == null || showMsg.isBlank()) ? LPAppConfig.MSG_DEFAULT_ERROR : showMsg);
        saveLogFile(null, null, null);
    }

    public LPAppException(String showMsg, Exception e, Class<?> clase, String metodo) {
        super((showMsg == null || showMsg.isBlank()) ? LPAppConfig.MSG_DEFAULT_ERROR : showMsg);
        saveLogFile(e.getMessage(), clase, metodo);
    }

    private void saveLogFile(String logMsg, Class<?> clase, String metodo) {
        String timestamp = LocalDateTime.now().format(FORMATTER);
        String className = (clase == null) ? LPAppConfig.MSG_DEFAULT_CLASS : clase.getSimpleName();
        String methodName = (metodo == null) ? LPAppConfig.MSG_DEFAULT_METHOD : metodo;
        logMsg = (logMsg == null || logMsg.isBlank()) ? LPAppConfig.MSG_DEFAULT_ERROR : logMsg;
        logMsg = String.format("╭─💀─ SHOW ❱❱ %s \n╰──── LOG  ❱❱ %s | %s.%s | %s", getMessage(), timestamp, className,
                methodName, logMsg);

        try (PrintWriter writer = new PrintWriter(new FileWriter(LPAppConfig.LOGFILE, true))) {
            System.err.println(LPCMDColor.BLUE + logMsg);
            writer.println(logMsg);
        } catch (Exception e) {
            System.err.println(LPCMDColor.RED + "[AppException.saveLogFile] ❱ " + e.getMessage());
        } finally {
            System.out.println(LPCMDColor.RESET);
        }
    }
}