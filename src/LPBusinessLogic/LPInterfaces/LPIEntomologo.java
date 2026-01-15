package LPBusinessLogic.LPInterfaces;

import java.util.List;
import LPBusinessLogic.LPEntities.LPHormiga;
import LPInfrastructure.LPAppException;

public interface LPIEntomologo {
    public List<String> LPetlAntNest();

    public List<String> LPetlAntFood();

    public LPHormiga LPAlimentarAnt(LPHormiga hormiga, String alimento);

    public void LPPreparaAlimento(String alimento) throws LPAppException;
}
