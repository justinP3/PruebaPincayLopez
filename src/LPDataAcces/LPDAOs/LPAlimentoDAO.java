package LPDataAcces.LPDAOs;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import LPDataAcces.LPDTOs.LPAlimentoDTO;
import LPDataAcces.LPHelpers.LPDataHelperSQLiteDAO;
import LPInfrastructure.LPAppException;

public class LPAlimentoDAO extends LPDataHelperSQLiteDAO<LPAlimentoDTO> {
    public LPAlimentoDAO() throws LPAppException {

        super(LPAlimentoDTO.class, "LPAlimento", "IdAlimento");
    }

    @Override
    public boolean delete(Integer id) throws LPAppException {
        String sql = "DELETE FROM LPAlimento WHERE IdAlimento = ?";
        try (PreparedStatement stmt = openConnection().prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new LPAppException(null, e, getClass(), "delete");
        }
    }

    @Override
    public List<LPAlimentoDTO> readAll() throws LPAppException {
        List<LPAlimentoDTO> list = new ArrayList<>();
        String sql = "SELECT * FROM LPAlimento";
        try (PreparedStatement stmt = openConnection().prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                list.add(mapResultSetToEntity(rs));
            }
        } catch (SQLException e) {
            throw new LPAppException(null, e, getClass(), "readAll");
        }
        return list;
    }

    @Override
    public LPAlimentoDTO readBy(Integer id) throws LPAppException {
        String sql = "SELECT * FROM LPAlimento WHERE IdAlimento = ?";
        try (PreparedStatement stmt = openConnection().prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next() ? mapResultSetToEntity(rs) : null;
            }
        } catch (SQLException e) {
            throw new LPAppException(null, e, getClass(), "readBy");
        }
    }
}