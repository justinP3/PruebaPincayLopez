package LPBusinessLogic.LPEntities;

import java.util.List;

import LPDataAcces.LPDTOs.LPHormigaDTO;
import LPInfrastructure.LPAppException;

public class LPHLarva extends LPHormiga {

    public LPHormigaDTO getLarva(int id) throws LPAppException {
        data = factory.getBy(id);
        return data;
    }

    public List<LPHormigaDTO> getLarvas() throws LPAppException {
        return factory.getAll();
    }

    @Override
    public String toString() {
        return "HLarva {}";
    }
}
