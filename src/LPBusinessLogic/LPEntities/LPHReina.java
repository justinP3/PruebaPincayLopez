package LPBusinessLogic.LPEntities;

import java.util.List;

import LPDataAcces.LPDTOs.LPHormigaDTO;
import LPInfrastructure.LPAppException;

public class LPHReina extends LPHormiga {

    public LPHormigaDTO getReina(int id) throws LPAppException {
        data = factory.getBy(id);
        return data;
    }

    public List<LPHormigaDTO> getReinas() throws LPAppException {
        return factory.getAll();
    }

    @Override
    public String toString() {
        return "HReina {}";
    }
}
