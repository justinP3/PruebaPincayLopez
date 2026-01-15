package LPDataAcces.LPDAOs;

import LPDataAcces.LPDTOs.*;
import LPDataAcces.LPHelpers.LPDataHelperSQLiteDAO;
import LPInfrastructure.*;

public class LPAlimentoDAO extends LPDataHelperSQLiteDAO<LPAlimentoDTO> {
    public LPAlimentoDAO() throws LPAppException {

        super(LPAlimentoDTO.class, "LPAlimento", "IdAlimento");
    }
}