//  © 2K26 ❱──💀──❰ pat_mic ? code is life : life is code
package LPBusinessLogic;

import java.util.List;

import LPDataAcces.LPInterfaces.LPIDAO;
import LPInfrastructure.LPAppException;

public class PjFactoryBL<T> {
    private final LPIDAO<T> oDAO;

    public PjFactoryBL(Class<? extends LPIDAO<T>> classDAO) {
        try {
            this.oDAO = classDAO.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            LPAppException er = new LPAppException("Error al instanciar classDAO<T>", e, getClass(), "FactoryBL(...)");
            throw new RuntimeException(er);
        }
    }

    // Constructor que usa un Supplier para crear la instancia de T
    // public FactoryBL(Supplier<LPIDAO<T>> supplier) {
    // this.oDAO = supplier.get();
    // }

    public List<T> getAll() throws LPAppException {
        return oDAO.readAll();
    }

    public T getBy(Integer id) throws LPAppException {
        return oDAO.readBy(id);
    }

    public boolean add(T oT) throws LPAppException {
        return oDAO.create(oT);
    }

    public boolean upd(T oT) throws LPAppException {
        return oDAO.update(oT);
    }

    public boolean del(Integer id) throws LPAppException {
        return oDAO.delete(id);
    }
}
