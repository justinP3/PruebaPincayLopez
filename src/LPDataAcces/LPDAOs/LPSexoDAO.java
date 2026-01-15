package LPDataAcces.LPDAOs;

import LPDataAcces.LPDTOs.LPSexoDTO;
import LPDataAcces.LPHelpers.LPDataHelperSQLiteDAO;
import LPInfrastructure.LPAppException;

public class LPSexoDAO extends LPDataHelperSQLiteDAO<LPSexoDTO> {
    public LPSexoDAO() throws LPAppException {
        super(LPSexoDTO.class, "Sexo", "IdSexo");
    }
}
