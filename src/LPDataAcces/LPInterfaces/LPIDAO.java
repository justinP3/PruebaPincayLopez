package LPDataAcces.LPInterfaces;

import java.util.List;

import LPInfrastructure.LPAppException;;

public interface LPIDAO<T> {
    List<T> readAll() throws LPAppException;

    T readBy(Integer id) throws LPAppException;

    boolean create(T entity) throws LPAppException;

    boolean update(T entity) throws LPAppException;

    boolean delete(Integer id) throws LPAppException;

    Integer getMaxReg() throws LPAppException;
}
