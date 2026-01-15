package LPBusinessLogic.LPEntities;

import LPDataAcces.LPDAOs.LPHormigaDAO;
import LPBusinessLogic.LPFactoryBL;
import LPDataAcces.LPDTOs.LPHormigaDTO;

public abstract class LPHormiga {
    protected LPFactoryBL<LPHormigaDTO> factory = new LPFactoryBL<>(LPHormigaDAO.class);
    public LPHormigaDTO data = new LPHormigaDTO();

    // protected HormigaDAO hormigaDAO;
    // protected Hormiga() throws AppException {
    // this.hormigaDAO = new HormigaDAO();
    // }

    // public FactoryBL<LPHormigaDTO> factory = new FactoryBL<>(() -> {
    // try {
    // return new HormigaDAO();
    // } catch (Exception e) {
    // new RuntimeException();
    // }
    // });
}
