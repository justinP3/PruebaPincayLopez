package LPDataAcces.LPDAOs;

import LPDataAcces.LPDTOs.*;
import LPDataAcces.LPHelpers.LPDataHelperSQLiteDAO;
import LPInfrastructure.*;

public class LPAlimentoTipoDAO extends LPDataHelperSQLiteDAO<LPAlimentoTipoDTO> {
    public LPAlimentoTipoDAO() throws LPAppException {
        super(LPAlimentoTipoDTO.class, "LPAlimentoTipo", "IdAlimentoTipo");
    }
}
