package LPDataAcces.LPDAOs;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import LPDataAcces.LPDTOs.LPHormigaDTO;
import LPDataAcces.LPDTOs.LPVWHormigaDTO;
import LPDataAcces.LPHelpers.LPDataHelperSQLiteDAO;
import LPInfrastructure.LPAppException;

public class LPHormigaDAO extends LPDataHelperSQLiteDAO<LPHormigaDTO> {
    public LPHormigaDAO() throws LPAppException {
        super(LPHormigaDTO.class, "LPHormiga", "IdHormiga");
    }

    public List<LPVWHormigaDTO> readAllvwHormiga() throws LPAppException {
        LPVWHormigaDTO dto;
        List<LPVWHormigaDTO> lst = new ArrayList<>();
        String query = " SELECT IdHormiga"
                + "  ,Tipo         "
                + "  ,Sexo         "
                + "  ,EstadoHormiga"
                + "  ,Nombre       "
                + "  ,Estado       "
                + "  ,FechaCreacion"
                + "  ,FechaModifica"
                + "  FROM vwHormiga";
        try {
            Connection conn = openConnection(); // conectar a DB
            Statement stmt = conn.createStatement(); // CRUD : select * ...
            ResultSet rs = stmt.executeQuery(query); // ejecutar la
            while (rs.next()) {
                dto = new LPVWHormigaDTO(rs.getString(1) // IdHormiga
                        , rs.getString(2) // Tipo
                        , rs.getString(3) // Sexo
                        , rs.getString(4) // EstadoHormiga
                        , rs.getString(5) // Nombre
                        , rs.getString(7) // Estado
                        , rs.getString(8) // FechaCreacion
                        , rs.getString(9) // FechaModifica
                );
                lst.add(dto);
            }
        } catch (SQLException e) {
            throw new LPAppException("Ups... porblemas con la vista", e, getClass(), "getVWHormiga()");
        }
        return lst;
    }
}